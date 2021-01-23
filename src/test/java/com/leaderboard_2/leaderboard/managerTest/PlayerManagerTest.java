package com.leaderboard_2.leaderboard.managerTest;

import com.leaderboard_2.leaderboard.base.BaseTest;
import com.leaderboard_2.leaderboard.manager.PlayerManager;
import com.leaderboard_2.leaderboard.models.converter.CreatePlayerRequestConverter;
import com.leaderboard_2.leaderboard.models.converter.UuidConverter;
import com.leaderboard_2.leaderboard.models.dto.CreatePlayerDto;
import com.leaderboard_2.leaderboard.models.dto.GetProfileDto;
import com.leaderboard_2.leaderboard.models.dto.PlayerDto;
import com.leaderboard_2.leaderboard.models.request.CreatePlayerRequest;
import com.leaderboard_2.leaderboard.models.response.CreatePlayerResponse;
import com.leaderboard_2.leaderboard.models.response.GetProfileResponse;
import com.leaderboard_2.leaderboard.service.PlayerService;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PlayerManagerTest extends BaseTest {

    @InjectMocks
    private PlayerManager playerManager;

    @Mock
    private PlayerService playerService;

    @Mock
    private CreatePlayerRequestConverter createPlayerRequestConverter;

    @Mock
    private UuidConverter uuidConverter;

    @Test
    void shouldCreatePlayer() {
        //given
        CreatePlayerRequest createPlayerRequest = mock(CreatePlayerRequest.class);
        CreatePlayerDto createPlayerDto = mock(CreatePlayerDto.class);
        PlayerDto playerDto = mock(PlayerDto.class);

        when(createPlayerRequestConverter.apply(createPlayerRequest)).thenReturn(createPlayerDto);
        when(playerService.createPlayer(createPlayerDto)).thenReturn(playerDto);

        //when
        CreatePlayerResponse response = playerManager.createPlayer(createPlayerRequest);

        //then
        InOrder inOrder = Mockito.inOrder(createPlayerRequestConverter, playerService);
        inOrder.verify(createPlayerRequestConverter).apply(createPlayerRequest);
        inOrder.verify(playerService).createPlayer(createPlayerDto);
        inOrder.verifyNoMoreInteractions();

        assertThat(response.getPlayerDto()).isEqualTo(playerDto);
    }

    @Test
    void shouldGetPlayerFromService() {
        //given
        String uuid = UUID.randomUUID().toString();
        GetProfileDto getPlayerDto = mock(GetProfileDto.class);
        PlayerDto playerDto = mock(PlayerDto.class);

        when(uuidConverter.apply(uuid)).thenReturn(getPlayerDto);
        when(playerService.getProfile(getPlayerDto)).thenReturn(playerDto);

        //when
        GetProfileResponse response = playerManager.getProfile(uuid);

        //then
        InOrder inOrder = Mockito.inOrder(uuidConverter, playerService);
        inOrder.verify(uuidConverter).apply(uuid);
        inOrder.verify(playerService).getProfile(getPlayerDto);
        inOrder.verifyNoMoreInteractions();

        assertThat(response.getPlayerDto()).isEqualTo(playerDto);

    }

}