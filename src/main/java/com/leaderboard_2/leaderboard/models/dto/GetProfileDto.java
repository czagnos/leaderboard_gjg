package com.leaderboard_2.leaderboard.models.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetProfileDto {
    private String uuid;
}
