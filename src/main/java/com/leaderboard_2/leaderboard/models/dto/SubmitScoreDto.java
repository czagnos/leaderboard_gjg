package com.leaderboard_2.leaderboard.models.dto;


import com.leaderboard_2.leaderboard.entity.AbstractEntity;
import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@Builder
public class SubmitScoreDto{

    private Double score;
    private String uuid;
    private ZonedDateTime timestamp;
    private String country;

}
