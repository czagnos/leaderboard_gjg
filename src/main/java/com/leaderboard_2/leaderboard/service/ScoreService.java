package com.leaderboard_2.leaderboard.service;


import com.leaderboard_2.leaderboard.entity.Score;
import com.leaderboard_2.leaderboard.models.dto.SubmitScoreDto;
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

    public SubmitScoreDto submitScore(SubmitScoreDto submitScoreDto){
        redisService.cacheScore(submitScoreDto);
        Score score = persistScore(submitScoreDto);

        return SubmitScoreDto.builder().uuid(score.getUserId())
                .score(score.getScore())
                .build();
    }

    private Score persistScore(SubmitScoreDto submitScoreDto){
        Score score = new Score();
        score.setUserId(submitScoreDto.getUuid());
        score.setScore(submitScoreDto.getScore());
        return scoreRepo.save(score);
    }
}
