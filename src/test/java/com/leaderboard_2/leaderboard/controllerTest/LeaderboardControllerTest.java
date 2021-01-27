package com.leaderboard_2.leaderboard.controllerTest;


import com.leaderboard_2.leaderboard.base.BaseTest;
import com.leaderboard_2.leaderboard.controller.leaderboardController;
import com.leaderboard_2.leaderboard.manager.LeaderboardManager;
import com.leaderboard_2.leaderboard.models.dto.PlayerLeaderboardDto;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.mockito.InOrder;
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

    @Before
    void setupAll(){

    }


    @Test
    void shouldGetLeaderboard(){
      //given
        List<PlayerLeaderboardDto> playerLeaderboard  =  mock(List.class);

        Mockito.when(leaderboardManager.getLeaderboard()).thenReturn(playerLeaderboard);

      //when
        List<PlayerLeaderboardDto> playerLeaderboardDto = leaderboardController.leaderboard();

      //then
       verify(leaderboardManager).getLeaderboard();

        assertThat(playerLeaderboardDto.equals(playerLeaderboard));

    }

    @Test
    void shouldGetLeaderboardWithPageNumber(){
        //given
        List<PlayerLeaderboardDto> playerLeaderboard  = mock(List.class);
         int pageNum = 2;

        when(leaderboardManager.getLeaderboardWithPageNumber(pageNum)).thenReturn(playerLeaderboard);

        //when
        List<PlayerLeaderboardDto> playerLeaderboardDto = leaderboardController.leaderboardWithPageNumber(pageNum);

        //then
        verify(leaderboardManager).getLeaderboardWithPageNumber(pageNum);


        assertThat(playerLeaderboardDto.equals(playerLeaderboard));

    }

    @Test
    void shouldGetLeaderboardWithCountry(){
        //given
        List<PlayerLeaderboardDto> playerLeaderboard  =  mock(List.class);
         int pageNum = 2;
         String country = "tr";
         when(leaderboardManager.getLeaderboardWithCountry(country , pageNum)).thenReturn(playerLeaderboard);

        //when
        List<PlayerLeaderboardDto> playerLeaderboardDto = leaderboardController.leaderboardWithCountry( country, pageNum );

        //then
        verify(leaderboardManager).getLeaderboardWithCountry(country, pageNum);


        assertThat(playerLeaderboardDto.equals(playerLeaderboard));

    }


}
