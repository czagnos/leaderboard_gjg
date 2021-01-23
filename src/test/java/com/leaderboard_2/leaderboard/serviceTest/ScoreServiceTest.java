package com.leaderboard_2.leaderboard.serviceTest;

import com.leaderboard_2.leaderboard.base.BaseTest;
import com.leaderboard_2.leaderboard.entity.Score;
import com.leaderboard_2.leaderboard.models.converter.ScoreConverter;
import com.leaderboard_2.leaderboard.models.dto.SubmitScoreDto;
import com.leaderboard_2.leaderboard.repository.ScoreRepo;
import com.leaderboard_2.leaderboard.service.PlayerService;
import com.leaderboard_2.leaderboard.service.RedisService;
import com.leaderboard_2.leaderboard.service.ScoreService;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoSettings;
import org.reactivestreams.Publisher;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;
import static reactor.core.publisher.Mono.when;

public class ScoreServiceTest extends BaseTest {


    @InjectMocks
    ScoreService scoreService;

    @Mock
    ScoreRepo scoreRepo;

    @Mock
    RedisService redisService;

    @Mock
    ScoreConverter scoreConverter;

    @Mock
    ScoreService scoreServiceMock;


    @Test
    void shouldSubmitScoreToRepo(){
        //given
        SubmitScoreDto submitScoreDto = mock(SubmitScoreDto.class);
        SubmitScoreDto returnSubmitScoreDto = mock(SubmitScoreDto.class);
        Score score = new Score();
         scoreServiceMock = new ScoreService(scoreRepo ,redisService,scoreConverter);

        Mockito.when(scoreServiceMock.persistScore(submitScoreDto)).thenReturn(score);
        Mockito.when(scoreConverter.get(score)).thenReturn(returnSubmitScoreDto);
        //when
        SubmitScoreDto response = scoreService.submitScore(submitScoreDto);

        //then
        InOrder inOrder = Mockito.inOrder(scoreConverter);
        inOrder.verify(scoreConverter).get(score);
        inOrder.verifyNoMoreInteractions();

        assertThat(response.equals(returnSubmitScoreDto));

    }

    @Test
    void shouldPersistScore(){
        //given
        SubmitScoreDto submitScoreDto = mock(SubmitScoreDto.class);
        Score score = new Score();
        Score submittedScore = new Score();

        Mockito.when(scoreRepo.save(score)).thenReturn(submittedScore);
        //when
        Score response = scoreService.persistScore(submitScoreDto);

        //then
        verify(scoreRepo).save(score);

        assertThat(response.equals(score));



    }
}
