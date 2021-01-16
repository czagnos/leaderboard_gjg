package com.leaderboard_2.leaderboard.models.converter;

import com.leaderboard_2.leaderboard.entity.Player;
import com.leaderboard_2.leaderboard.models.dto.CreatePlayerDto;
import com.leaderboard_2.leaderboard.models.dto.PlayerDto;
import com.leaderboard_2.leaderboard.models.request.CreatePlayerRequest;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CreatePlayerRequestConverter implements Function<CreatePlayerRequest, CreatePlayerDto> {

    @Override
    public CreatePlayerDto apply(CreatePlayerRequest createPlayerRequest){
        return CreatePlayerDto.builder()
                .name(createPlayerRequest.getName())
                .country(createPlayerRequest.getCountry())
                .build();
    }
}
