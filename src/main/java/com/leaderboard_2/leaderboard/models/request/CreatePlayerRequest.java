package com.leaderboard_2.leaderboard.models.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePlayerRequest {

    @NotEmpty
    private String name;

    @NotEmpty
    private String country;
    


}
