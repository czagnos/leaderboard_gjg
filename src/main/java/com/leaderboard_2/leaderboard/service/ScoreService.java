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
    private final PlayerRepo playerRepo;

    /*
     *Submit score to database and cache
     *
     *@param submitscoredto
     * @return submitscoredto
     */
    public SubmitScoreDto submitScore(SubmitScoreDto submitScoreDto){
        if(playerRepo.checkId(submitScoreDto.getUuid())==0)throw new IllegalArgumentException("Wrong uid.");
        redisService.cacheScore(submitScoreDto);
      //  dynamoService.cacheScoreWithCountry(submitScoreDto);
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
    private Score persistScore(SubmitScoreDto submitScoreDto){
        Score score = new Score();
        score.setUserId(submitScoreDto.getUuid());
        score.setScore(submitScoreDto.getScore());
        scoreRepo.updateScore(score.getScore(),score.getUserId());
        return score;
    }
}
