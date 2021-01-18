package com.leaderboard_2.leaderboard.manager;


import com.leaderboard_2.leaderboard.entity.Player;
import com.leaderboard_2.leaderboard.entity.Score;
import com.leaderboard_2.leaderboard.models.dto.PlayerDto;
import com.leaderboard_2.leaderboard.models.dto.PlayerLeaderboardDto;
import com.leaderboard_2.leaderboard.service.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.mapping.Array;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class LeaderboardManager {
        private final RedisService redisService;

        /*
        * Getting list of players which is on top 10.
        *
        *@return list of player as data transfer object of player
         */
        public List<PlayerLeaderboardDto> showLeaderboard(){

              return redisService.getLeaderboardTop();
                
        }
        /*
         * Getting list of players which is on specified page.
         *
         *@param Number of page which is specified as integer
         *@return list of player as data transfer object of player
         */
        public List<PlayerLeaderboardDto> showLeaderboardWithPageNumber(int pageNumber){
            if(pageNumber<1)throw new IllegalArgumentException("Invalid page number.");
            return redisService.getLeaderboardWithPageNumber( pageNumber);

    }
}
