package com.Futkaradze.controller;

import com.Futkaradze.models.Chat;
import com.Futkaradze.models.User;
import com.Futkaradze.request.CreateChatRequest;
import com.Futkaradze.service.ChatService;
import com.Futkaradze.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatController {

    private final ChatService chatService;
    private final UserService userService;

    @Autowired
    public ChatController(ChatService chatService, UserService userService) {
        this.chatService = chatService;
        this.userService = userService;
    }

    @PostMapping("/api/chats")
    public Chat createChat(@RequestHeader("Authorization") String jwt,
                           @RequestBody CreateChatRequest req) throws Exception {

        User reqUser = userService.findUserByJwt(jwt);

        User user2 = userService.findUserById(req.getUserId());

        Chat chat = chatService.createChat(reqUser, user2);
        return chat;
    }

    @GetMapping("/api/chats")
    public List<Chat> findusersChat(@RequestHeader("Authorization") String jwt) {

        User user = userService.findUserByJwt(jwt);

        List <Chat> chats = chatService.findUsersChat(user.getId());

        return chats;
    }
}

