package com.leaderboard_2.leaderboard.models.converter;

import com.leaderboard_2.leaderboard.entity.Player;
import com.leaderboard_2.leaderboard.models.dto.PlayerDto;
import com.leaderboard_2.leaderboard.models.dto.PlayerLeaderboardDto;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class PlayerConverter implements Function<Player, PlayerDto> {

    @Override
    public PlayerDto apply(Player player){


        return PlayerDto.builder().uuid(player.getUid())
                .uuid(player.getUid())
                .name(player.getName())
                .score(player.getScore() == null ? 0 : player.getScore().getScore())
                .rank(player.getRank())
                .build();
    }


    public PlayerDto show(Player player){
        return PlayerDto.builder()
                .uuid(player.getUid())
                .name(player.getName())
                .score(player.getScore().getScore())
                .rank(player.getRank())
                .build();
    }

    public PlayerLeaderboardDto leaderboard(Player player){
            return PlayerLeaderboardDto.builder()
                    .rank(player.getRank())
                    .score(player.getScore().getScore())
                    .name(player.getName())
                    .country(player.getCountry())
                    .build();
    }
}
