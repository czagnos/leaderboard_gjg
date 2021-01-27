package com.leaderboard_2.leaderboard.models.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.leaderboard_2.leaderboard.models.dto.SubmitScoreDto;
import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@Builder
public class SubmitScoreResponse {


    @JsonProperty(value = "user_id")
    private String uuid;

    @JsonProperty(value = "score_worth")
    private Double score;

    @JsonProperty(value = "timestamp")
    private ZonedDateTime timestamp;


}
