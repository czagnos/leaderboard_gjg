package com.leaderboard_2.leaderboard.models.converter;

import com.leaderboard_2.leaderboard.models.dto.SubmitScoreDto;
import com.leaderboard_2.leaderboard.models.request.SubmitScoreRequest;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class SubmitScoreRequestConverter implements Function<SubmitScoreRequest, SubmitScoreDto> {

    @Override
    public SubmitScoreDto apply(SubmitScoreRequest submitScoreRequest){
        return SubmitScoreDto.builder()
                .uuid(submitScoreRequest.getUuid())
                .score(submitScoreRequest.getScore())
                .country(submitScoreRequest.getCountry())
                .build();
    }

}
