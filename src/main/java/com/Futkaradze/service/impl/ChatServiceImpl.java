package com.Futkaradze.service.impl;

import com.Futkaradze.models.Chat;
import com.Futkaradze.models.User;
import com.Futkaradze.repository.ChatRepository;
import com.Futkaradze.service.ChatService;
import com.Futkaradze.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService {

    private final UserService userService;
    private final ChatRepository chatRepository;

    @Autowired
    public ChatServiceImpl(UserService userService, ChatRepository chatRepository) {
        this.userService = userService;
        this.chatRepository = chatRepository;
    }

    @Override
    public Chat createChat(User reqUser, User user2) {

        Chat isExist = chatRepository.findChatByUserId(user2, reqUser);

        if(isExist!=null) {
            return isExist;
        }

        Chat chat = new Chat();
        chat.getUsers().add(user2);
        chat.getUsers().add(reqUser);
        chat.setTimestamp(LocalDateTime.now());

        return chatRepository.save(chat);
    }

    @Override
    public Chat findChatById(Long chatId) throws Exception {

        Optional<Chat> opt = chatRepository.findById(chatId);

        if(opt.isEmpty()) {
            throw new Exception("Chat with id - " + chatId + " not found");
        }

        return opt.get();
    }

    @Override
    public List<Chat> findUsersChat(Long userId) {
        return chatRepository.findByUsersId(userId);
    }
}