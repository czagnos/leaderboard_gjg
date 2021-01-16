package com.leaderboard_2.leaderboard.manager;


import com.leaderboard_2.leaderboard.models.converter.SubmitScoreRequestConverter;
import com.leaderboard_2.leaderboard.models.dto.SubmitScoreDto;
import com.leaderboard_2.leaderboard.models.request.SubmitScoreRequest;
import com.leaderboard_2.leaderboard.models.response.SubmitScoreResponse;
import com.leaderboard_2.leaderboard.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScoreManager {

    private final ScoreService scoreService;
    private final SubmitScoreRequestConverter submitScoreRequestConverter;

    public SubmitScoreResponse submitScore(SubmitScoreRequest submitScoreRequest){
        SubmitScoreDto submitScoreDto = submitScoreRequestConverter.apply(submitScoreRequest);
        SubmitScoreDto savedScore = scoreService.submitScore(submitScoreDto);

        return SubmitScoreResponse.builder().uuid(savedScore.getUuid()).score(savedScore.getScore()).build();
    }

}
