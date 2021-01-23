package com.leaderboard_2.leaderboard.managerTest;

import com.amazonaws.services.elasticloadbalancing.model.BackendServerDescription;
import com.leaderboard_2.leaderboard.base.BaseTest;
import com.leaderboard_2.leaderboard.manager.LeaderboardManager;
import com.leaderboard_2.leaderboard.models.dto.PlayerLeaderboardDto;
import com.leaderboard_2.leaderboard.service.RedisService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static reactor.core.publisher.Mono.when;


public class LeaderboardManagerTest extends BaseTest {

    @InjectMocks
    private  LeaderboardManager leaderboardManager;

    @Mock
    private  RedisService redisService;

    @Before
    void setupAll(){

    }


    @Test
    void shouldGetLeaderboardFromServices(){
        //given
        List<PlayerLeaderboardDto> playerLeaderboard =  mock(List.class);
        Mockito.when(redisService.getLeaderboardTop()).thenReturn(playerLeaderboard);

        //when
        List<PlayerLeaderboardDto> response = leaderboardManager.getLeaderboard();

        //then
        InOrder inOrder = inOrder(redisService);
        inOrder.verify(redisService).getLeaderboardTop();
        inOrder.verifyNoMoreInteractions();

        assertThat(response.equals(playerLeaderboard));
    }

    @Test
    void shouldGetLeaderboardWithPageNumberFromServices(){
        //given
        List<PlayerLeaderboardDto> playerLeaderboard =  mock(List.class);

        int pageNum = 2;

        Mockito.when(redisService.getLeaderboardWithPageNumber(pageNum)).thenReturn(playerLeaderboard);

        //when
        List<PlayerLeaderboardDto> response = leaderboardManager.getLeaderboardWithPageNumber(pageNum);

        //then
        InOrder inOrder = inOrder(redisService);
        inOrder.verify(redisService).getLeaderboardWithPageNumber(pageNum);
        inOrder.verifyNoMoreInteractions();


        assertThat(response.equals(playerLeaderboard));
    }

    @Test
    void shouldGetLeaderboardWithCountryFromServices(){
        //given
        List<PlayerLeaderboardDto> playerLeaderboard =  mock(List.class);

        String country = "tr";

        int pageNum = 2;

        Mockito.when(redisService.getLeaderboardWithCountry(country,pageNum)).thenReturn(playerLeaderboard);

        //when
        List<PlayerLeaderboardDto> response = leaderboardManager.getLeaderboardWithCountry(country,pageNum);

        //then
        InOrder inOrder = inOrder(redisService);
        inOrder.verify(redisService).getLeaderboardWithCountry(country,pageNum);
        inOrder.verifyNoMoreInteractions();

        assertThat(response.equals(playerLeaderboard));
    }

}
