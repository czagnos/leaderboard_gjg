package com.leaderboard_2.leaderboard.managerTest;

import com.leaderboard_2.leaderboard.base.BaseTest;
import com.leaderboard_2.leaderboard.manager.ScoreManager;
import com.leaderboard_2.leaderboard.models.converter.SubmitScoreRequestConverter;
import com.leaderboard_2.leaderboard.models.dto.SubmitScoreDto;
import com.leaderboard_2.leaderboard.models.request.SubmitScoreRequest;
import com.leaderboard_2.leaderboard.models.response.SubmitScoreResponse;
import com.leaderboard_2.leaderboard.service.ScoreService;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ScoreManagerTest extends BaseTest {

    @InjectMocks
    private ScoreManager scoreManager;

    @Mock
    private ScoreService scoreService;

    @Mock
    private SubmitScoreRequestConverter submitScoreRequestConverter;


    @Test
    void shouldSubmitScoreToService() {
        //given
        SubmitScoreRequest submitScoreRequest = mock(SubmitScoreRequest.class);
        SubmitScoreDto submitScoreDto = mock(SubmitScoreDto.class);

        when(submitScoreRequestConverter.apply(submitScoreRequest)).thenReturn(submitScoreDto);
        when(scoreService.submitScore(submitScoreDto)).thenReturn(submitScoreDto);

        //when
        SubmitScoreResponse response = scoreManager.submitScore(submitScoreRequest);

        //then
        InOrder inOrder = Mockito.inOrder(submitScoreRequestConverter, scoreService);
        inOrder.verify(submitScoreRequestConverter).apply(submitScoreRequest);
        inOrder.verify(scoreService).submitScore(submitScoreDto);
        inOrder.verifyNoMoreInteractions();

        assertThat(response.getScore()).isEqualTo(submitScoreDto.getScore());
    }
}
