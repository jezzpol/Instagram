package com.Futkaradze.service;


import com.Futkaradze.exception.UserException;
import com.Futkaradze.models.User;

import java.util.List;

public interface UserService {

    public User registerUser(User user);

    public User findUserById(Long userId) throws UserException;

    public User findUserByEmail(String email);

    public User followUser(Long userId1, Long userId2) throws UserException; // 1 - кто подписан, 2 - на кого подписан

    public User updateUser(User user, Long userId) throws UserException;

    public List<User> searchUser(String query); // query - запрос

    public User findUserByJwt(String jwt);
}