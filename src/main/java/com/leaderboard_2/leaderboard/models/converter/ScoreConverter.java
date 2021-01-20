package com.leaderboard_2.leaderboard.models.converter;

import com.leaderboard_2.leaderboard.entity.Score;
import com.leaderboard_2.leaderboard.models.dto.SubmitScoreDto;
import com.leaderboard_2.leaderboard.models.request.SubmitScoreRequest;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ScoreConverter implements Function<Score, SubmitScoreDto>  {

    @Override
    public SubmitScoreDto apply(Score score){
        return SubmitScoreDto.builder()
                .uuid(score.getUserId())
                .score(score.getScore())
                .country(score.getCountry())
                .build();
    }

}
