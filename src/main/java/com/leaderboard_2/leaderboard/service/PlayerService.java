package com.leaderboard_2.leaderboard.service;


import com.leaderboard_2.leaderboard.entity.Player;
import com.leaderboard_2.leaderboard.entity.Score;
import com.leaderboard_2.leaderboard.models.converter.PlayerConverter;
import com.leaderboard_2.leaderboard.models.dto.CreatePlayerDto;
import com.leaderboard_2.leaderboard.models.dto.PlayerDto;
import com.leaderboard_2.leaderboard.models.dto.ShowProfileDto;
import com.leaderboard_2.leaderboard.repository.PlayerRepo;
import com.leaderboard_2.leaderboard.repository.ScoreRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlayerService {

        private final PlayerRepo playerRepo;
        private final PlayerConverter pLayerConverter;
            private final ScoreRepo scoreRepo;

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
            String uid = UUID.randomUUID().toString();
            Score score = new Score();
            if(playerRepo.checkName(createPlayerDto.getName()) > 0 ) throw new IllegalArgumentException("Your name is used, please try a different name.");
            player.setUid(uid);
            player.setName(createPlayerDto.getName());
            player.setCountry(createPlayerDto.getCountry());
            score.setScore(0.0);
            score.setUserId(uid);
            player.setScore(score);
            scoreRepo.save(score);
            try (Jedis jedis = new Jedis("localhost")){
                jedis.zadd("leaderboard", score.getScore(), score.getUserId());
            }
            player.setRank(getPlayerRank(uid));
            Player createdPlayer = playerRepo.save(player);
            return pLayerConverter.apply(createdPlayer);
        }
            /*
             *find player from database and return player as type of response body
             *
             * @param showprofile
             * @return playerdto
             */
        public PlayerDto showProfile(ShowProfileDto showProfileDto){
            if(playerRepo.checkId(showProfileDto.getUuid()) ==0)throw new IllegalArgumentException("Wrong uid.");
            Player showedPlayer = playerRepo.findByUid(showProfileDto.getUuid());
            showedPlayer.setRank(getPlayerRank(showProfileDto.getUuid()));

            return pLayerConverter.show(showedPlayer);
        }
            /*
             * Getting rank of player.
             *
             * @param uuid as sring
             * @return currentrank of player
             */
        public int getPlayerRank(String uuid){
            Long currentRank;
            try (Jedis jedis = new Jedis("localhost")){
                currentRank = Long.sum(jedis.zrevrank("leaderboard",uuid),1);
            }
            return currentRank.intValue();
        }

}
