package com.leaderboard_2.leaderboard.service;

import com.leaderboard_2.leaderboard.models.dto.SubmitScoreDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisService {

    public void cacheScore(SubmitScoreDto submitScoreDto){
        try (Jedis jedis = new Jedis("localhost")){
            String result = jedis.set(submitScoreDto.getUuid().toString(), submitScoreDto.getScore().toString());
            log.info(result);
            jedis.zadd("leaderboard", submitScoreDto.getScore(), submitScoreDto.getUuid().toString());
        }
    }

    public List<Tuple> getLeaderboardTop() {
        try (Jedis jedis = new Jedis("localhost")){
            return new ArrayList<>(jedis.zrevrangeByScoreWithScores("leaderboard",99999, 0 ));
        }
    }

    public List<Tuple> getLeaderboardTopbyCountry() {
        try (Jedis jedis = new Jedis("localhost")){
            return new ArrayList<>(jedis.zscan());
        }
    }

}
