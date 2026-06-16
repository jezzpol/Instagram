package com.Futkaradze.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Futkaradze.models.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    public List<Message> findByChatId(Long chatId);

}
