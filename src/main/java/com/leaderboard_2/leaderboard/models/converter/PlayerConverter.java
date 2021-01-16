package com.leaderboard_2.leaderboard.models.converter;

import com.leaderboard_2.leaderboard.entity.Player;
import com.leaderboard_2.leaderboard.models.dto.PlayerDto;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class PlayerConverter implements Function<Player, PlayerDto> {

    @Override
    public PlayerDto apply(Player player){
        return PlayerDto.builder().uuid(player.getUid())
                .name(player.getName())
                .score(player.getScore() == null ? 0 : player.getScore().getScore())
                .country(player.getCountry())
                .rank(-1)
                .build();
    }


    public PlayerDto show(Player player){
        return PlayerDto.builder().uuid(player.getUid())
                .name(player.getName())
                .score(player.getScore().getScore())
                .country(player.getCountry())
               // .rank()
                .build();
    }
}
