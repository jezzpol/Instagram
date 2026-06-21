package com.Futkaradze.service.impl;

import com.Futkaradze.config.JwtProvider;
import com.Futkaradze.exception.UserException;
import com.Futkaradze.models.User;
import com.Futkaradze.repository.UserRepository;
import com.Futkaradze.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User registerUser(User user) {

        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(user.getPassword());
        newUser.setId(user.getId());

        User savedUser = userRepository.save(newUser);

        return savedUser;
    }

    @Override
    public User findUserById(Long userId) throws UserException {
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            return user.get();
        }

        throw new UserException("User not exist with userId " + userId);

    }

    @Override
    public User findUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        return user;
    }

    @Override
    public User followUser(Long reqUserId, Long userId2) throws UserException {

        User reqUser = findUserById(reqUserId); // добавляем user1 в список подписчиков к user2

        User user2 = findUserById(userId2); // добавляем user 2 в список подписок

        user2.getFollowers().add(Math.toIntExact(reqUser.getId())); // Math.toIntExact - чтобы наш Long переделать в Integer
        reqUser.getFollowings().add(Math.toIntExact(user2.getId()));

        userRepository.save(reqUser);
        userRepository.save(user2);

        return reqUser; // ЕСЛИ ЧТО, ИСПРАВИТЬ userId2 в строчке  User user2 = findUserById(userId2) на reqUser

    }

    @Override
    public User updateUser(User user, Long userId) throws UserException {
        Optional<User> user1 = userRepository.findById(userId);

        if (user1.isEmpty()) {
            throw new UserException("User not exist with userId " + userId);
        }

        User oldUser = user1.get();

        if (user.getFirstName() != null) {
            oldUser.setFirstName((user.getFirstName()));
        }

        if (user.getLastName() != null) {
            oldUser.setLastName((user.getLastName()));
        }
        if (user.getEmail() != null) {
            oldUser.setEmail((user.getEmail()));
        }

        if(user.getGender() != null){
            oldUser.setGender((user.getGender()));
        }

        User updatedUser = userRepository.save(oldUser);

        return updatedUser;
    }

    @Override
    public List<User> searchUser(String query) {
        return userRepository.searchUser(query);
    }

    @Override
    public User findUserByJwt(String jwt) {

        String email = JwtProvider.getEmailFromJwtToken(jwt);

        User user = userRepository.findUserByEmail(email);

        return user;
    }
}
