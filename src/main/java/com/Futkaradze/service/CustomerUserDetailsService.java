package com.Futkaradze.service;

import com.Futkaradze.models.User;
import com.Futkaradze.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomerUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findUserByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with email " + username);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);

        // В данном коде, после того, как был найден пользователь в базе данных,
        // создается экземпляр класса User с помощью конструктора,
        // который принимает три параметра: username - это email пользователя,
        // который был передан в метод loadUserByUsername().
        // password - это пароль пользователя, который должен быть получен из базы данных.
        // authorities - это список прав доступа, которыми обладает пользователь. В данном коде,
        // список прав доступа пустой, поэтому экземпляр User будет создан без прав доступа.
    }
}

