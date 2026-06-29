package com.Futkaradze.controller;

import com.Futkaradze.models.Reels;
import com.Futkaradze.models.User;
import com.Futkaradze.service.ReelsService;
import com.Futkaradze.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReelsController {

    private final ReelsService reelsService;
    private final UserService userService;

    @Autowired
    public ReelsController(ReelsService reelsService, UserService userService) {
        this.reelsService = reelsService;
        this.userService = userService;
    }

    @PostMapping("/api/reels")
    public Reels createReels(@RequestBody Reels reel, @RequestHeader("Authorization") String jwt){

        User reqUser = userService.findUserByJwt(jwt);

        Reels createdReels = reelsService.createReel(reel, reqUser);

        return createdReels;
    }

    @GetMapping("/api/reels")
    public List<Reels> findAllReels() {

        List<Reels> reels = reelsService.findAllReels();

        return reels;
    }

    @GetMapping("/api/reels/user/{userId}")
    public List<Reels> findUsersReels (@PathVariable Long userId) throws Exception {

        List<Reels> reels = reelsService.findUsersReel(userId);

        return reels;
    }
}
