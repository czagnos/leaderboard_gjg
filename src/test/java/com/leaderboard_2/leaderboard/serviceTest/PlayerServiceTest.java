package com.leaderboard_2.leaderboard.serviceTest;

import com.leaderboard_2.leaderboard.base.BaseTest;
import com.leaderboard_2.leaderboard.entity.Player;
import com.leaderboard_2.leaderboard.entity.Score;
import com.leaderboard_2.leaderboard.models.converter.PlayerConverter;
import com.leaderboard_2.leaderboard.models.converter.ScoreConverter;
import com.leaderboard_2.leaderboard.models.dto.CreatePlayerDto;
import com.leaderboard_2.leaderboard.models.dto.GetProfileDto;
import com.leaderboard_2.leaderboard.models.dto.PlayerDto;
import com.leaderboard_2.leaderboard.models.dto.SubmitScoreDto;
import com.leaderboard_2.leaderboard.repository.PlayerRepo;
import com.leaderboard_2.leaderboard.service.PlayerService;
import com.leaderboard_2.leaderboard.service.RedisService;
import com.leaderboard_2.leaderboard.service.ScoreService;
import org.apache.regexp.RE;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.in;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlayerServiceTest extends BaseTest {

    @InjectMocks
    private PlayerService playerService;

    @Mock
    private  PlayerRepo playerRepo;

    @Mock
    private  PlayerConverter pLayerConverter;

    @Mock
    private ScoreConverter scoreConverter;

    @Mock
    private ScoreService scoreService;

    @Mock
    private RedisService redisService;


    @Test
    void shouldCreatePlayerInRepo() {
        //given
        PlayerDto playerDto = mock(PlayerDto.class);
        SubmitScoreDto submitScoreDto = mock(SubmitScoreDto.class);
        CreatePlayerDto createPlayerDto = mock(CreatePlayerDto.class);

        Player player = new Player();
        Player createdPlayer = new Player();
        Score score = new Score();
        Score submittedScore = new Score();


        when(scoreConverter.apply(score)).thenReturn(submitScoreDto);
        when(scoreService.persistScore(submitScoreDto)).thenReturn(submittedScore);
        when(playerRepo.save(player)).thenReturn(createdPlayer);
        when(pLayerConverter.apply(createdPlayer)).thenReturn(playerDto);

        //when
          PlayerDto response  = playerService.createPlayer(createPlayerDto);

        //then
        InOrder inOrder = Mockito.inOrder(scoreConverter,scoreService,playerRepo,pLayerConverter);
        inOrder.verify(scoreConverter).apply(score);
        inOrder.verify(scoreService).persistScore(submitScoreDto);
        inOrder.verify(playerRepo).save(player);
        inOrder.verify(pLayerConverter).apply(createdPlayer);
        inOrder.verifyNoMoreInteractions();

        assertThat(response.equals(playerDto));

    }

    @Test
    void shouldGetProfileFromRepo(){
        //given
        Player player = mock(Player.class);
        GetProfileDto getProfileDto = mock(GetProfileDto.class);
        PlayerDto playerDto = mock(PlayerDto.class);

        when(playerRepo.findByUid(getProfileDto.getUuid())).thenReturn(player);
        when(pLayerConverter.get(player)).thenReturn(playerDto);

        //when
        PlayerDto response = playerService.getProfile(getProfileDto);

        //then
        InOrder inOrder = Mockito.inOrder(playerRepo,pLayerConverter);
        inOrder.verify(playerRepo).findByUid(getProfileDto.getUuid());
        inOrder.verify(pLayerConverter).get(player);
        inOrder.verifyNoMoreInteractions();

        assertThat(response.equals(playerDto));

    }
}
