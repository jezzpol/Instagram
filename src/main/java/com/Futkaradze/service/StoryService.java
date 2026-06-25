package com.Futkaradze.service;

import com.Futkaradze.models.Story;
import com.Futkaradze.models.User;

import java.util.List;

public interface StoryService {

    public Story createStory (Story story, User user);

    public List<Story> findStoryByUserId(Long userId) throws Exception;
}
