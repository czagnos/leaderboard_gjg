package com.leaderboard_2.leaderboard.service;


import com.leaderboard_2.leaderboard.entity.Score;
import com.leaderboard_2.leaderboard.models.converter.ScoreConverter;
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
        scoreRepo.save(score);
        return score;
    }

    /*
     * Adding score to database.
     *
     *@param submitscoredto
     * @return score object
     */
    public Score persistSubmittedScore(SubmitScoreDto submitScoreDto){
        Score score = new Score();
        score =  scoreRepo.findByUserId(submitScoreDto.getUuid()).get();
        score.setScore(submitScoreDto.getScore());
        score.setCountry(submitScoreDto.getCountry());
        return score;
    }


}
