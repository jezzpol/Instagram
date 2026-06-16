package com.Futkaradze.repository;

import com.Futkaradze.models.Chat;
import com.Futkaradze.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {

    public List<Chat> findByUsersId(Long userId);

    @Query("select c from Chat c Where :user member of c.users And :reqUser Member of c.users")
    public Chat findChatByUserId(@Param("user") User user, @Param("reqUser") User reqUser);

}
