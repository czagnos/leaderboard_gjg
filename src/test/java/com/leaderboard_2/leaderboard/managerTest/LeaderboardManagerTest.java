package com.leaderboard_2.leaderboard.managerTest;

import com.amazonaws.services.elasticloadbalancing.model.BackendServerDescription;
import com.leaderboard_2.leaderboard.base.BaseTest;
import com.leaderboard_2.leaderboard.manager.LeaderboardManager;
import com.leaderboard_2.leaderboard.models.dto.PlayerLeaderboardDto;
import com.leaderboard_2.leaderboard.service.RedisService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

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


    @Test
    void shouldGetLeaderboardFromServices(){
        //given

        List<PlayerLeaderboardDto> playerLeaderboard  = (List<PlayerLeaderboardDto>) mock(PlayerLeaderboardDto.class);

        Mockito.when(redisService.getLeaderboardTop()).thenReturn(playerLeaderboard);

        //when
        List<PlayerLeaderboardDto> response = leaderboardManager.getLeaderboard();

        //then
        verify(redisService).getLeaderboardTop();
        verifyNoMoreInteractions();

        assertThat(response.equals(playerLeaderboard));
    }

    @Test
    void shouldGetLeaderboardWithPageNumberFromServices(){
        //given

        List<PlayerLeaderboardDto> playerLeaderboard  = (List<PlayerLeaderboardDto>) mock(PlayerLeaderboardDto.class);
        int pageNum =  mock(int.class);

        Mockito.when(redisService.getLeaderboardWithPageNumber(pageNum)).thenReturn(playerLeaderboard);

        //when
        List<PlayerLeaderboardDto> response = leaderboardManager.getLeaderboardWithPageNumber(pageNum);

        //then
        verify(redisService).getLeaderboardWithPageNumber(pageNum);
        verifyNoMoreInteractions();

        assertThat(response.equals(playerLeaderboard));
    }

    @Test
    void shouldGetLeaderboardWithCountryFromServices(){
        //given

        List<PlayerLeaderboardDto> playerLeaderboard  = (List<PlayerLeaderboardDto>) mock(PlayerLeaderboardDto.class);

        int pageNum = mock(int.class);

        String country = mock(String.class);

        Mockito.when(redisService.getLeaderboardWithCountry(country,pageNum)).thenReturn(playerLeaderboard);

        //when
        List<PlayerLeaderboardDto> response = leaderboardManager.getLeaderboardWithCountry(country,pageNum);

        //then
        verify(redisService).getLeaderboardWithCountry(country,pageNum);
        verifyNoMoreInteractions();

        assertThat(response.equals(playerLeaderboard));
    }

}
