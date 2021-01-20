package com.leaderboard_2.leaderboard.serviceTest;

import com.leaderboard_2.leaderboard.entity.Score;
import com.leaderboard_2.leaderboard.models.dto.SubmitScoreDto;
import com.leaderboard_2.leaderboard.repository.ScoreRepo;
import com.leaderboard_2.leaderboard.service.PlayerService;
import com.leaderboard_2.leaderboard.service.RedisService;
import com.leaderboard_2.leaderboard.service.ScoreService;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;
import static reactor.core.publisher.Mono.when;

public class ScoreServiceTest {


    @InjectMocks
    ScoreService scoreService;

    @Mock
    ScoreRepo scoreRepo;

    @Test
    void shouldSubmitScoreToRepo(){
        //given
        SubmitScoreDto submitScoreDto = mock(SubmitScoreDto.class);

        //when
        SubmitScoreDto response = scoreService.submitScore(submitScoreDto);

        //then
        verifyNoInteractions();

        assertThat(!response.getUuid().isEmpty());

    }

    @Test
    void shouldPersistScore(){
        //given
        SubmitScoreDto submitScoreDto = mock(SubmitScoreDto.class);
        Score score = mock(Score.class);

        Mockito.when(scoreRepo.findByUserId(submitScoreDto.getUuid())).thenReturn(score);
        Mockito.when(scoreRepo.save(score));
        //when
        Score response = scoreService.persistScore(submitScoreDto);

        //then
        InOrder inOrder = inOrder(scoreRepo);
        inOrder.verify(scoreRepo).findByUserId(submitScoreDto.getUuid());
        inOrder.verify(scoreRepo).save(score);

        assertThat(response.equals(score));



    }
}
