package com.leaderboard_2.leaderboard.controller;


import com.leaderboard_2.leaderboard.manager.LeaderboardManager;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Tuple;

import java.util.List;

@RestController
@AllArgsConstructor
public class leaderboardController {

        private final LeaderboardManager leaderboardManager;



    @GetMapping ("/v1/leaderboard")
    public List<Tuple> leaderboard(){
        return leaderboardManager.showLeaderboard();
    }
}
