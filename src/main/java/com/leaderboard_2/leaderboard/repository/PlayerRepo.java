package com.leaderboard_2.leaderboard.repository;


import java.util.UUID;
import com.leaderboard_2.leaderboard.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepo extends JpaRepository<Player, UUID> {

}
