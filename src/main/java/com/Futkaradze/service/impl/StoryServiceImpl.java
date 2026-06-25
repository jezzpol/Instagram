package com.Futkaradze.service.impl;

import com.Futkaradze.models.Story;
import com.Futkaradze.models.User;
import com.Futkaradze.repository.StoryRepository;
import com.Futkaradze.service.StoryService;
import com.Futkaradze.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StoryServiceImpl implements StoryService {

    private final StoryRepository storyRepository;
    private final UserService userService;

    @Autowired
    public StoryServiceImpl(StoryRepository storyRepository, UserService userService) {
        this.storyRepository = storyRepository;
        this.userService = userService;
    }

    @Override
    public Story createStory(Story story, User user) {

        Story createdStory = new Story();

        createdStory.setCaption(story.getCaption());
        createdStory.setImage(story.getImage());
        createdStory.setUser(user);
        createdStory.setTimestamp(LocalDateTime.now());

        return storyRepository.save(createdStory);
    }

    @Override
    public List<Story> findStoryByUserId(Long userId) throws Exception {

        User user = userService.findUserById(userId);

        return storyRepository.findByUserId(userId);
    }
}
