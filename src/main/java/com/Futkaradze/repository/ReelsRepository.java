package com.Futkaradze.repository;

import com.Futkaradze.models.Reels;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReelsRepository extends JpaRepository<Reels, Long> {

    public List<Reels> findByUserId(Long userId);

}
