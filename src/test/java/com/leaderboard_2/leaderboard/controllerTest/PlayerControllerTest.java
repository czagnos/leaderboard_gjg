package com.leaderboard_2.leaderboard.controllerTest;

import com.leaderboard_2.leaderboard.base.BaseTest;
import com.leaderboard_2.leaderboard.controller.playerController;
import com.leaderboard_2.leaderboard.manager.PlayerManager;
import com.leaderboard_2.leaderboard.models.request.CreatePlayerRequest;
import com.leaderboard_2.leaderboard.models.response.CreatePlayerResponse;
import com.leaderboard_2.leaderboard.models.response.GetProfileResponse;
import org.junit.jupiter.api.*;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class PlayerControllerTest extends BaseTest {

    @InjectMocks
    private  playerController playerController;

    @Mock
    private PlayerManager playerManager;



    @Test
    void shouldCreatePlayer() {
        //given
        CreatePlayerRequest createPlayerRequest = mock(CreatePlayerRequest.class);
        CreatePlayerResponse createPlayerResponse= mock(CreatePlayerResponse.class);

        when(playerManager.createPlayer(createPlayerRequest)).thenReturn(createPlayerResponse);

        //when
        CreatePlayerResponse response = playerManager.createPlayer(createPlayerRequest);

        //then
        verify(playerManager).createPlayer(createPlayerRequest);

        assertThat(response.getPlayerDto()).isEqualTo(createPlayerResponse.getPlayerDto());
    }

    @Test
    void shouldGetPlayer() {
        //given
        String uuid = UUID.randomUUID().toString();
        GetProfileResponse getPlayerResponse = mock(GetProfileResponse.class);

        when(playerManager.getProfile(uuid)).thenReturn(getPlayerResponse);

        //when
        GetProfileResponse response = playerManager.getProfile(uuid);

        //then
        verify(playerManager).getProfile(uuid);

        assertThat(response.getPlayerDto()).isEqualTo(getPlayerResponse.getPlayerDto());
    }



}
