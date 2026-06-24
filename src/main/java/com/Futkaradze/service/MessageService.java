package com.Futkaradze.service;

import com.Futkaradze.models.Message;
import com.Futkaradze.models.User;

import java.util.List;

public interface MessageService {

    public Message createMessage(User user, Long chatId, Message req) throws Exception;

    public List<Message> findChatsMessages(Long chatId) throws Exception;

}
