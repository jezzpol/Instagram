package com.Futkaradze.service;


import com.Futkaradze.models.Chat;
import com.Futkaradze.models.User;

import java.util.List;

public interface ChatService {

    public Chat createChat(User reqUser, User user2);

    public Chat findChatById(Long chatId) throws Exception;

    public List<Chat> findUsersChat (Long userId);

}
