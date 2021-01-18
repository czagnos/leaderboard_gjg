package com.leaderboard_2.leaderboard.models.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerLeaderboardDto {
    private String name;
    private Double score;
    private Integer rank;
    private String country;
}
