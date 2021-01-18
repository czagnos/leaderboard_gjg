package com.leaderboard_2.leaderboard.service;

import com.leaderboard_2.leaderboard.entity.Player;
import com.leaderboard_2.leaderboard.entity.Score;
import com.leaderboard_2.leaderboard.models.converter.PlayerConverter;
import com.leaderboard_2.leaderboard.models.converter.UuidConverter;
import com.leaderboard_2.leaderboard.models.dto.PlayerDto;
import com.leaderboard_2.leaderboard.models.dto.PlayerLeaderboardDto;
import com.leaderboard_2.leaderboard.models.dto.ShowProfileDto;
import com.leaderboard_2.leaderboard.models.dto.SubmitScoreDto;
import com.leaderboard_2.leaderboard.repository.PlayerRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisService {

    private final UuidConverter uuidConverter;
    private final PlayerRepo playerRepo;
    private final PlayerService playerService;
    private final PlayerConverter playerConverter;
    public void cacheScore(SubmitScoreDto submitScoreDto){
        try (Jedis jedis = new Jedis("localhost")){
            jedis.zadd("leaderboard", submitScoreDto.getScore(), submitScoreDto.getUuid().toString());
        }
    }

    /*
    *   Getting top 10 player from cache and return their type as response body
    *
    *@return list of playerleaderboardDto
     */
    public List<PlayerLeaderboardDto> getLeaderboardTop() {
        Set<String> uidTop;

        try (Jedis jedis = new Jedis("localhost") ){
        //    uidTop =   jedis.zrevrangeByScore("leaderboard",10000000, 0 );
            uidTop = jedis.zrevrange("leaderboard",0, 9);

        }
        List<PlayerLeaderboardDto> pageList = new ArrayList<>();
        for (String uuid : uidTop) {
            ShowProfileDto showProfileDto = uuidConverter.apply(uuid);
             Player leaderboardPlayer    = playerRepo.findByUid(showProfileDto.getUuid());
            leaderboardPlayer.setRank(playerService.getPlayerRank(uuid));
            PlayerLeaderboardDto leaderboardPlayerdto = playerConverter.leaderboard(leaderboardPlayer);
            pageList.add(leaderboardPlayerdto);
        }
        return pageList;

    }
    /*
     *Getting 10 player of specific page on the leaderboard.
     *
     *@return list of playerleaderboardDto
     */
    public List<PlayerLeaderboardDto> getLeaderboardWithPageNumber(int pageNum) {
        Set<String> uidPerPage;
        try (Jedis jedis = new Jedis("localhost") ){
          //  uidPerPage =   jedis.zrevrangeByScore("leaderboard", 10000000, 0);
            uidPerPage = jedis.zrevrange("leaderboard",(pageNum-1)*10, pageNum*10 -1);

        }
        List<PlayerLeaderboardDto> pageList = new ArrayList<>();
        for (String uuid : uidPerPage) {
            ShowProfileDto showProfileDto = uuidConverter.apply(uuid);
            Player leaderboardPlayer    = playerRepo.findByUid(showProfileDto.getUuid());
            leaderboardPlayer.setRank(playerService.getPlayerRank(uuid));
            PlayerLeaderboardDto leaderboardPlayerdto = playerConverter.leaderboard(leaderboardPlayer);
            pageList.add(leaderboardPlayerdto);
        }
        return pageList;

    }
}
