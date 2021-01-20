package com.leaderboard_2.leaderboard.controllerTest;


import com.leaderboard_2.leaderboard.base.BaseTest;
import com.leaderboard_2.leaderboard.controller.leaderboardController;
import com.leaderboard_2.leaderboard.manager.LeaderboardManager;
import com.leaderboard_2.leaderboard.models.dto.PlayerLeaderboardDto;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

public class LeaderboardControllerTest extends BaseTest {


    @InjectMocks
    private  leaderboardController leaderboardController;

    @Mock
    private LeaderboardManager leaderboardManager;




    @Test
    void shouldGetLeaderboard(){
      //given
        List<PlayerLeaderboardDto> playerLeaderboard  = (List<PlayerLeaderboardDto>) mock(PlayerLeaderboardDto.class);

        Mockito.when(leaderboardManager.getLeaderboard()).thenReturn(playerLeaderboard);

      //when
        List<PlayerLeaderboardDto> playerLeaderboardDto = leaderboardController.leaderboard();

      //then
        verify(leaderboardManager).getLeaderboard();
        verifyNoMoreInteractions();

        assertThat(playerLeaderboardDto.equals(playerLeaderboard));

    }

    @Test
    void shouldGetLeaderboardWithPageNumber(){
        //given
        List<PlayerLeaderboardDto> playerLeaderboard  = (List<PlayerLeaderboardDto>) mock(PlayerLeaderboardDto.class);
         int pageNum =mock(int.class);

        when(leaderboardManager.getLeaderboardWithPageNumber(pageNum)).thenReturn(playerLeaderboard);

        //when
        List<PlayerLeaderboardDto> playerLeaderboardDto = leaderboardController.leaderboardWithPageNumber(pageNum);

        //then
        verify(leaderboardManager).getLeaderboardWithPageNumber(pageNum);
        verifyNoMoreInteractions();


        assertThat(playerLeaderboardDto.equals(playerLeaderboard));

    }

    @Test
    void shouldGetLeaderboardWithCountry(){
        //given
        List<PlayerLeaderboardDto> playerLeaderboard  = (List<PlayerLeaderboardDto>) mock(PlayerLeaderboardDto.class);
         int pageNum = mock(int.class);
         String country = mock(String.class);
         when(leaderboardManager.getLeaderboardWithCountry(country , pageNum)).thenReturn(playerLeaderboard);

        //when
        List<PlayerLeaderboardDto> playerLeaderboardDto = leaderboardController.leaderboardWithCountry( country, pageNum );

        //then
        verify(leaderboardManager).getLeaderboardWithCountry(country, pageNum);
        verifyNoMoreInteractions();


        assertThat(playerLeaderboardDto.equals(playerLeaderboard));

    }


}
