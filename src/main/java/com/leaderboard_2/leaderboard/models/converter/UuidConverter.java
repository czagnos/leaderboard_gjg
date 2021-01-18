package com.leaderboard_2.leaderboard.models.converter;


import com.leaderboard_2.leaderboard.models.dto.ShowProfileDto;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UuidConverter implements Function<String, ShowProfileDto> {

        @Override
        public ShowProfileDto apply(String uuid){
            return ShowProfileDto.builder().uuid(uuid).build();
        }
}
