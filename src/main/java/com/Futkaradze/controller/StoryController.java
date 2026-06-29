package com.Futkaradze.controller;

import com.Futkaradze.models.Story;
import com.Futkaradze.models.User;
import com.Futkaradze.service.StoryService;
import com.Futkaradze.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StoryController {

    private final StoryService storyService;
    private final UserService userService;

    @Autowired
    public StoryController(StoryService storyService, UserService userService) {
        this.storyService = storyService;
        this.userService = userService;
    }

    @PostMapping("/api/story")
    public Story createStory(@RequestBody Story story, @RequestHeader("Authorization") String jwt){

        User reqUser = userService.findUserByJwt(jwt);

        Story createdStory = storyService.createStory(story, reqUser);

        return createdStory;
    }

    @GetMapping("/api/story/user/{userId}")
    public List<Story> findUsersStory(@PathVariable Long userId, @RequestHeader("Authorization") String jwt) throws Exception {

        User reqUser = userService.findUserByJwt(jwt);

        List<Story> stories = storyService.findStoryByUserId(userId);

        return stories;
    }
}

