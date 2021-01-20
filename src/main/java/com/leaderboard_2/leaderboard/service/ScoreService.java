package com.leaderboard_2.leaderboard.service;


import com.leaderboard_2.leaderboard.entity.Score;
import com.leaderboard_2.leaderboard.models.dto.SubmitScoreDto;
import com.leaderboard_2.leaderboard.repository.PlayerRepo;
import com.leaderboard_2.leaderboard.repository.ScoreRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScoreService {

    private final ScoreRepo scoreRepo;
    private final RedisService redisService;

    /*
     *Submit score to database and cache
     *
     *@param submitscoredto
     * @return submitscoredto
     */
    public SubmitScoreDto submitScore(SubmitScoreDto submitScoreDto){
        redisService.cacheScore(submitScoreDto);
        Score score = persistScore(submitScoreDto);

        return SubmitScoreDto.builder().uuid(score.getUserId())
                .score(score.getScore())
                .build();
    }
    /*
     * Adding score to database.
     *
     *@param submitscoredto
     * @return score object
     */
    public Score persistScore(SubmitScoreDto submitScoreDto){
        Score score = scoreRepo.findByUserId(submitScoreDto.getUuid());
        score.setUserId(submitScoreDto.getUuid());
        score.setScore(submitScoreDto.getScore());
        score.setCountry(submitScoreDto.getCountry());
        scoreRepo.save(score);
        return score;
    }
}
