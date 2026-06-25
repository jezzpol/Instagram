package com.Futkaradze.service.impl;

import com.Futkaradze.models.Reels;
import com.Futkaradze.models.User;
import com.Futkaradze.repository.ReelsRepository;
import com.Futkaradze.service.ReelsService;
import com.Futkaradze.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReelsServiceImpl implements ReelsService {

    private final ReelsRepository reelsRepository;
    private final UserService userService;

    @Autowired
    public ReelsServiceImpl(ReelsRepository reelsRepository, UserService userService) {
        this.reelsRepository = reelsRepository;
        this.userService = userService;
    }

    @Override
    public Reels createReel(Reels reel, User user) {

        Reels createReel = new Reels();

        createReel.setTittle(reel.getTittle());
        createReel.setUser(user);
        createReel.setVideo(reel.getVideo());

        return reelsRepository.save(createReel);

    }

    @Override
    public List<Reels> findAllReels() {

        return reelsRepository.findAll();

    }

    @Override
    public List<Reels> findUsersReel(Long userId) throws Exception {

        userService.findUserById(userId);

        return  reelsRepository.findByUserId(userId);
    }
}
