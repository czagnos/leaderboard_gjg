package com.leaderboard_2.leaderboard.repository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.leaderboard_2.leaderboard.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PlayerRepo extends JpaRepository<Player, UUID> {
                  Player findByUid(String uid);
                    int countAllBy();

                  Optional<Player> findByName(String name);




}
