package com.leaderboard_2.leaderboard.controllerTest;

import com.leaderboard_2.leaderboard.base.BaseTest;
import com.leaderboard_2.leaderboard.controller.scoreController;
import com.leaderboard_2.leaderboard.manager.ScoreManager;
import com.leaderboard_2.leaderboard.models.request.CreatePlayerRequest;
import com.leaderboard_2.leaderboard.models.request.SubmitScoreRequest;
import com.leaderboard_2.leaderboard.models.response.CreatePlayerResponse;
import com.leaderboard_2.leaderboard.models.response.SubmitScoreResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


public class ScoreController extends BaseTest {

    @InjectMocks
    private scoreController scoreController;

    @Mock
    private ScoreManager scoreManager;

    @Test
    void shouldSubmitPlayer(){
        //given
        SubmitScoreRequest submitScoreRequest = mock(SubmitScoreRequest.class);
        SubmitScoreResponse submitScoreResponse= mock(SubmitScoreResponse.class);

        when(scoreManager.submitScore(submitScoreRequest)).thenReturn(submitScoreResponse);

        //when
        SubmitScoreResponse response = scoreController.submitScore(submitScoreRequest);

        //then
        verify(scoreManager).submitScore(submitScoreRequest);
        verifyNoMoreInteractions();

        assertThat(response.getScore().equals(submitScoreResponse.getScore()));


    }






}
