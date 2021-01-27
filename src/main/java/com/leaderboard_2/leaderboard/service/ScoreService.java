package com.leaderboard_2.leaderboard.service;


import com.leaderboard_2.leaderboard.entity.Score;
import com.leaderboard_2.leaderboard.models.converter.ScoreConverter;
import com.leaderboard_2.leaderboard.models.dto.SubmitScoreDto;
import com.leaderboard_2.leaderboard.repository.PlayerRepo;
import com.leaderboard_2.leaderboard.repository.ScoreRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScoreService {

    private final ScoreRepo scoreRepo;
    private final RedisService redisService;
    private final ScoreConverter scoreConverter;
    /*
     *Submit score to database and cache
     *
     *@param submitscoredto
     * @return submitscoredto
     */
    public SubmitScoreDto submitScore(SubmitScoreDto submitScoreDto){
        redisService.cacheScore(submitScoreDto);
        Score score = persistScore(submitScoreDto);
        SubmitScoreDto returnSubmitScoreDto = scoreConverter.get(score);
        return returnSubmitScoreDto;
    }
    /*
     * Adding score to database.
     *
     *@param submitscoredto
     * @return score object
     */
    public Score persistScore(SubmitScoreDto submitScoreDto){
        Score score = scoreRepo.findByUserId(submitScoreDto.getUuid()).orElse(new Score());
        score.setUserId(submitScoreDto.getUuid());
        score.setScore(submitScoreDto.getScore());
        score.setCountry(submitScoreDto.getCountry());
        score.setUpdatedAt( ZonedDateTime.now(ZoneId.of("UTC")));
        scoreRepo.save(score);
        score.setUpdatedAt(null);
        return score;
    }




}
