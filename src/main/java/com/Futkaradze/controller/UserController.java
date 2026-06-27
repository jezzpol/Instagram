package com.Futkaradze.controller;

import com.Futkaradze.exception.UserException;
import com.Futkaradze.models.User;
import com.Futkaradze.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.Futkaradze.repository.UserRepository;

import java.util.List;

@RestController
@ResponseBody
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/api/users")
    public List<User> getUsers() {

        List<User> users = userRepository.findAll();

        return users;
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {

        User savedUser = userService.registerUser(user);

        return savedUser;
    }

    @GetMapping("/api/users/{userId}")
    public User getUserById(@PathVariable("userId") Long id) throws UserException {

        User user = userService.findUserById(id);

        return user;
    }

    @PutMapping("/api/users/{userId}")
    public User updateUser(@RequestHeader("Authorization") String jwt, @RequestBody User user) throws UserException {

        User reqUser = userService.findUserByJwt(jwt);

        User updatedUser = userService.updateUser(user, reqUser.getId());

        return updatedUser;

    }

    @PutMapping("/api/users/follow/{userId2}")
    public User followUserHandler(@RequestHeader("Authorization") String jwt, @PathVariable Long userId2) throws UserException {

        User reqUser = userService.findUserByJwt(jwt);

        User user = userService.followUser(reqUser.getId(), userId2);

        return user;
    }

    @GetMapping("/api/users/search")
    public List<User> searchUser(@RequestParam("query") String query){

        List<User> users = userService.searchUser(query);

        return users;
    }
    @GetMapping("/api/users/profile")
    public User getUserFromToken(@RequestHeader("Authorization") String jwt) {

        User user = userService.findUserByJwt(jwt);

        user.setPassword(null);

        return user;
    }
}
