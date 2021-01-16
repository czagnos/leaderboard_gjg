package com.leaderboard_2.leaderboard.repository;

import com.leaderboard_2.leaderboard.entity.Player;
import com.leaderboard_2.leaderboard.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ScoreRepo extends JpaRepository<Score, UUID> {
                Score findByUserId(UUID userId);
}
