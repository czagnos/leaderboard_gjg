package com.leaderboard_2.leaderboard.service;

import com.leaderboard_2.leaderboard.entity.Player;
import com.leaderboard_2.leaderboard.models.converter.PlayerConverter;
import com.leaderboard_2.leaderboard.models.converter.UuidConverter;
import com.leaderboard_2.leaderboard.models.dto.PlayerLeaderboardDto;
import com.leaderboard_2.leaderboard.models.dto.GetProfileDto;
import com.leaderboard_2.leaderboard.models.dto.SubmitScoreDto;
import com.leaderboard_2.leaderboard.repository.PlayerRepo;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;



@Slf4j
@Service
@RequiredArgsConstructor
public class RedisService {




    private final UuidConverter uuidConverter;
    private final PlayerRepo playerRepo;
    private final PlayerConverter playerConverter;

    private   String redisHost = "localhost";
    private   int redisPort = 6379;


    public void cacheScore(SubmitScoreDto submitScoreDto){


        try (Jedis jedis = new Jedis(redisHost,redisPort)){
            jedis.zadd("leaderboard-" + submitScoreDto.getCountry(), submitScoreDto.getScore(), submitScoreDto.getUuid());
            jedis.zadd("leaderboard", submitScoreDto.getScore(), submitScoreDto.getUuid());
        }
    }

    /*
    *   Getting top 10 player from cache and return their type as response body
    *
    *@return list of playerleaderboardDto
     */
    public List<PlayerLeaderboardDto> getLeaderboardTop() {
        Set<String> uidTop;

        try (Jedis jedis = new Jedis(redisHost,redisPort)){
            uidTop = jedis.zrevrange("leaderboard",0, 9);

        }
        List<PlayerLeaderboardDto> pageList = new ArrayList<>();
        int index =  1 ;
        for (String uuid : uidTop) {
            GetProfileDto getProfileDto = uuidConverter.apply(uuid);
             Player leaderboardPlayer    = playerRepo.findByUid(getProfileDto.getUuid());
            leaderboardPlayer.setRank(index);
            PlayerLeaderboardDto leaderboardPlayerdto = playerConverter.leaderboard(leaderboardPlayer);
            pageList.add(leaderboardPlayerdto);
            index++;
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
        try (Jedis jedis = new Jedis(redisHost,redisPort)){
            uidPerPage = jedis.zrevrange("leaderboard",(pageNum-1)*10, pageNum*10 -1);

        }
        List<PlayerLeaderboardDto> pageList = new ArrayList<>();
        int index = (pageNum-1)*10 +1 ;
        for (String uuid : uidPerPage) {
            GetProfileDto showProfileDto = uuidConverter.apply(uuid);
            Player leaderboardPlayer    = playerRepo.findByUid(showProfileDto.getUuid());
            leaderboardPlayer.setRank(index);
            PlayerLeaderboardDto leaderboardPlayerdto = playerConverter.leaderboard(leaderboardPlayer);
            pageList.add(leaderboardPlayerdto);
            index++;
        }
        return pageList;

    }

    public List<PlayerLeaderboardDto> getLeaderboardWithCountry( String country, int pageNum) {
        Set<String> uidPerPage;
        try (Jedis jedis = new Jedis(redisHost,redisPort)){
            uidPerPage = jedis.zrevrange("leaderboard-"+country,(pageNum-1)*10, pageNum*10 -1);

        }
        List<PlayerLeaderboardDto> pageList = new ArrayList<>();
        int index = (pageNum-1)*10 +1 ;
        for (String uuid : uidPerPage) {
            GetProfileDto getProfileDto = uuidConverter.apply(uuid);
            Player leaderboardPlayer    = playerRepo.findByUid(getProfileDto.getUuid());
            leaderboardPlayer.setRank(index);
            PlayerLeaderboardDto leaderboardPlayerdto = playerConverter.leaderboard(leaderboardPlayer);
            pageList.add(leaderboardPlayerdto);
            index++;
        }
        return pageList;

    }

    public int getPlayerRank(String uuid){
        Long currentRank;
        try (Jedis jedis = new Jedis(redisHost,redisPort)){
            currentRank = Long.sum(jedis.zrevrank("leaderboard",uuid),1);
        }
        return currentRank.intValue();
    }

}
