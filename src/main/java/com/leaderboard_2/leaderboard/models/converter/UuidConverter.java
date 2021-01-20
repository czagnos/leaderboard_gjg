package com.leaderboard_2.leaderboard.models.converter;


import com.leaderboard_2.leaderboard.models.dto.GetProfileDto;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UuidConverter implements Function<String, GetProfileDto> {

        @Override
        public GetProfileDto apply(String uuid){
            return GetProfileDto.builder().uuid(uuid).build();
        }
}
