package com.leaderboard_2.leaderboard.service;


import com.leaderboard_2.leaderboard.entity.Player;
import com.leaderboard_2.leaderboard.entity.Score;
import com.leaderboard_2.leaderboard.models.converter.PlayerConverter;
import com.leaderboard_2.leaderboard.models.converter.ScoreConverter;
import com.leaderboard_2.leaderboard.models.dto.CreatePlayerDto;
import com.leaderboard_2.leaderboard.models.dto.PlayerDto;
import com.leaderboard_2.leaderboard.models.dto.GetProfileDto;
import com.leaderboard_2.leaderboard.models.dto.SubmitScoreDto;
import com.leaderboard_2.leaderboard.repository.PlayerRepo;
import com.leaderboard_2.leaderboard.repository.ScoreRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlayerService {

        private final PlayerRepo playerRepo;
        private final PlayerConverter pLayerConverter;
        private final ScoreConverter scoreConverter;
        private  final  ScoreService scoreService;
        private  final  RedisService redisService;



    /*
             * Insert new player to database of player
             * Insert score as 0 for new player as default
             * new player score is on the cache and calculated in leaderboard on bottom
             *
             *
             * @param createplayerdto
             * @return playerdto
             */
        public PlayerDto createPlayer(CreatePlayerDto createPlayerDto){
            Player player = new Player();
            Score score = new Score();
            String uid = UUID.randomUUID().toString();
            player.setUid(uid);
            player.setName(createPlayerDto.getName());
            player.setCountry(createPlayerDto.getCountry());
            player.setRank(playerRepo.countAllBy()+1);

            score.setUserId(uid);
            score.setScore(0.0);
            score.setCountry(createPlayerDto.getCountry());
            SubmitScoreDto submitScoreDto = scoreConverter.apply(score);
            score = scoreService.persistScore(submitScoreDto);

            player.setScore(score);
            Player createdPlayer = playerRepo.save(player);
            return pLayerConverter.apply(createdPlayer);
        }
            /*
             *find player from database and return player as type of response body
             *
             * @param showprofile
             * @return playerdto
             */
        public PlayerDto getProfile(GetProfileDto getProfileDto){

            Player showedPlayer = playerRepo.findByUid(getProfileDto.getUuid());
            showedPlayer.setRank(redisService.getPlayerRank(getProfileDto.getUuid()));
            return pLayerConverter.get(showedPlayer);
        }





}
