package com.leaderboard_2.leaderboard.controller;


import com.leaderboard_2.leaderboard.entity.Player;
import com.leaderboard_2.leaderboard.manager.LeaderboardManager;
import com.leaderboard_2.leaderboard.models.dto.PlayerDto;
import com.leaderboard_2.leaderboard.models.dto.PlayerLeaderboardDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Tuple;

import java.util.List;

@RestController
@AllArgsConstructor
public class leaderboardController {

        private final LeaderboardManager leaderboardManager;


/*
 * Getting request of show first page of leaderboard
 *
 * @return List of players in the top 10
 *
 */
    @GetMapping ("/v1/leaderboard")
    public List<PlayerLeaderboardDto> leaderboard(){
        return leaderboardManager.showLeaderboard();
    }

    /*
     * Getting request of show specific page of leaderboard
     *
     * @param  Page Number
     * @return List of players in specific page
     *
     */
    @GetMapping ("/v1/leaderboard/{pageNum}")
    public List<PlayerLeaderboardDto> leaderboardwithPageNumber(@PathVariable int pageNum){
        return leaderboardManager.showLeaderboardWithPageNumber( pageNum);
    }
}