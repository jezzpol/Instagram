package com.Futkaradze.repository;

import com.Futkaradze.models.Story;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoryRepository extends JpaRepository<Story, Long> {

    public List<Story> findByUserId(Long userId);
}
