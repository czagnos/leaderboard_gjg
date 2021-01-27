package com.leaderboard_2.leaderboard.manager;


import com.leaderboard_2.leaderboard.models.converter.SubmitScoreRequestConverter;
import com.leaderboard_2.leaderboard.models.dto.SubmitScoreDto;
import com.leaderboard_2.leaderboard.models.request.SubmitScoreRequest;
import com.leaderboard_2.leaderboard.models.response.SubmitScoreResponse;
import com.leaderboard_2.leaderboard.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class ScoreManager {

    private final ScoreService scoreService;
    private final SubmitScoreRequestConverter submitScoreRequestConverter;

    /*
     *@param  submitscorerequest body
     *@return submitscoreresponse body
     */
    public SubmitScoreResponse submitScore(SubmitScoreRequest submitScoreRequest){
        SubmitScoreDto submitScoreDto = submitScoreRequestConverter.apply(submitScoreRequest);
        SubmitScoreDto savedScore = scoreService.submitScore(submitScoreDto);

        return SubmitScoreResponse.builder().uuid(savedScore.getUuid()).score(savedScore.getScore()).timestamp(ZonedDateTime.now(ZoneId.of("UTC"))).build();
    }

}
