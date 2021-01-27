package com.leaderboard_2.leaderboard.models.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.leaderboard_2.leaderboard.models.dto.PlayerDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetProfileResponse {

    @JsonProperty(value = "player")
    private PlayerDto playerDto;


}
