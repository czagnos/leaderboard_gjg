package com.leaderboard_2.leaderboard.serviceTest;

import ch.qos.logback.classic.boolex.JaninoEventEvaluator;
import com.github.fppt.jedismock.RedisServer;
import com.leaderboard_2.leaderboard.base.BaseTest;
import com.leaderboard_2.leaderboard.entity.Player;
import com.leaderboard_2.leaderboard.models.converter.PlayerConverter;
import com.leaderboard_2.leaderboard.models.converter.UuidConverter;
import com.leaderboard_2.leaderboard.models.dto.GetProfileDto;
import com.leaderboard_2.leaderboard.models.dto.PlayerDto;
import com.leaderboard_2.leaderboard.models.dto.PlayerLeaderboardDto;
import com.leaderboard_2.leaderboard.models.dto.SubmitScoreDto;
import com.leaderboard_2.leaderboard.repository.PlayerRepo;
import com.leaderboard_2.leaderboard.service.RedisService;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

public class RedisServiceTest extends BaseTest {

//
//
//
//    @InjectMocks
//    RedisService redisService;
//
//    @Mock
//     UuidConverter uuidConverter;
//
//    @Mock
//     PlayerRepo playerRepo;
//
//    @Mock
//     PlayerConverter playerConverter;
//
//    @Mock
//    Jedis jedis;
//
//    @Mock
//    RedisServer server;
//
//    @Mock
//    List<PlayerLeaderboardDto> pageList;
//
//    @Mock
//    Set<String> uidTop;
//
//    @Before
//    void before() throws IOException {
//        server = RedisServer.newRedisServer(6379);  // bind to a random port
//        server.start();
//    }
//
//    @Test
//    void shouldCacheScore(){
//        //given
//        RedisService redisService = mock(RedisService.class);
//        SubmitScoreDto submitScoreDto = mock(SubmitScoreDto.class);
//
//        //when
//        redisService.cacheScore(submitScoreDto);
//
//       verify(redisService).cacheScore(submitScoreDto);
//
//    }
//
//    @Test
//    void shouldGetLeaderboard(){
//
//        GetProfileDto getProfileDto = mock(GetProfileDto.class);
//        Player player = mock(Player.class);
//        PlayerLeaderboardDto getPlayer = mock(PlayerLeaderboardDto.class);
//        Set<String> uidTop = new HashSet<>(Arrays.asList("7c9e3211-2a97-4b31-a416-3c54ad88a4c0", "d6470ed9-5d1d-4944-8f92-22ac2a6d1d55",
//                "e7f9e6b7-b192-4744-9134-e70a228dacf5","1f424e1e-3c2c-4e52-b297-d75c85d7b223",
//                "6f193d2a-dcca-49bf-b8d5-50084e29c284","4e640b79-040a-455d-99a1-f704ed13220d",
//                "f7a94c1d-b98e-4233-911e-63e489dd2b45","402bd2a8-26fc-4a23-bedf-30d25be354be",
//                "49808a66-965e-4133-aa97-2271e47dd5e9"));
//
//        for (Iterator<String> it = uidTop.iterator(); it.hasNext(); ) {
//            String uid = it.next();
//            when(uuidConverter.apply(uid)).thenReturn(getProfileDto);
//            when(playerRepo.findByUid(getProfileDto.getUuid())).thenReturn(player);
//            when(playerConverter.leaderboard(player)).thenReturn(getPlayer);
//            pageList.add(getPlayer);
//        }
//
//
//        List<PlayerLeaderboardDto> response = redisService.getLeaderboardTop();
//
//        InOrder inOrder = inOrder(uuidConverter,playerRepo,playerConverter,pageList);
//
//        for (Iterator<String> it = uidTop.iterator(); it.hasNext(); ) {
//            String uid = it.next();
//            inOrder.verify(uuidConverter).apply( uid);
//            inOrder.verify(playerRepo).findByUid(getProfileDto.getUuid());
//            inOrder.verify(playerConverter).leaderboard(player);
//            pageList.add(getPlayer);
//
//        }
//        inOrder.verifyNoMoreInteractions();
//
//        assertThat(response.containsAll(pageList));
//
//
//
//   /*     0 = "7c9e3211-2a97-4b31-a416-3c54ad88a4c0"
//        1 = "f7a94c1d-b98e-4233-911e-63e489dd2b45"
//        2 = "e7f9e6b7-b192-4744-9134-e70a228dacf5"
//        3 = "d6470ed9-5d1d-4944-8f92-22ac2a6d1d55"
//        4 = "6f193d2a-dcca-49bf-b8d5-50084e29c284"
//        5 = "4e640b79-040a-455d-99a1-f704ed13220d"
//        6 = "49808a66-965e-4133-aa97-2271e47dd5e9"
//        7 = "402bd2a8-26fc-4a23-bedf-30d25be354be"
//        8 = "1f424e1e-3c2c-4e52-b297-d75c85d7b223"*/
//
//
//    }

}
