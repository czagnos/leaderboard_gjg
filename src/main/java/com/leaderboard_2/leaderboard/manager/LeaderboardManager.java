package com.leaderboard_2.leaderboard.manager;


import com.leaderboard_2.leaderboard.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Tuple;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaderboardManager {
        private final RedisService redisService;


        public List<Tuple> showLeaderboard(){
                return redisService.getLeaderboardTop();
        }
}
