package com.Futkaradze.service;

import java.util.List;
import com.Futkaradze.models.Reels;
import com.Futkaradze.models.User;

public interface ReelsService {

    public Reels createReel(Reels reel, User user);

    public List<Reels> findAllReels();

    public List<Reels> findUsersReel(Long userId) throws Exception;

}

