package com.Futkaradze.controller;

import com.Futkaradze.models.Message;
import com.Futkaradze.models.User;
import com.Futkaradze.service.MessageService;
import com.Futkaradze.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {

    private final MessageService messageService;
    private final UserService userService;

    @Autowired
    public MessageController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    @PostMapping("/api/messages/chat/{chatId}")
    public Message createMessage(@RequestBody Message req,
                                 @RequestHeader("Authorization") String jwt,
                                 @PathVariable Long chatId) throws Exception {

        User user = userService.findUserByJwt(jwt);

        Message message = messageService.createMessage(user, chatId, req);

        return message; // good
    }

    @GetMapping("/api/messages/chat/{chatId}")
    public List<Message> findChatsMessage(@RequestHeader("Authorization") String jwt,
                                          @PathVariable Long chatId) throws Exception {

        User user = userService.findUserByJwt(jwt);

        List<Message> messages = messageService.findChatsMessages(chatId);

        return messages; // good
    }
}
