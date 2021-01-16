package com.leaderboard_2.leaderboard.models.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubmitScoreDto {

    private Double score;
    private String uuid;
    private Long timestamp;

}
