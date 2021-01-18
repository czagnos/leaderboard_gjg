package com.leaderboard_2.leaderboard.controller;

import com.leaderboard_2.leaderboard.manager.ScoreManager;
import com.leaderboard_2.leaderboard.models.request.SubmitScoreRequest;
import com.leaderboard_2.leaderboard.models.response.SubmitScoreResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class scoreController {


    private final ScoreManager scoreManager;


    /*
    * Getting request of submit a new score of player
    *
    * @param submitscorerequest body
    * @return submitscoreresponse body
     */
    @PostMapping("/v1/score/submit")
    public SubmitScoreResponse submitScore(@RequestBody SubmitScoreRequest submitScoreRequest){
        return scoreManager.submitScore(submitScoreRequest);
    }


}
