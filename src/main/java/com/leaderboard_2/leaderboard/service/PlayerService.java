package com.leaderboard_2.leaderboard.service;


import com.leaderboard_2.leaderboard.entity.Player;
import com.leaderboard_2.leaderboard.models.converter.PlayerConverter;
import com.leaderboard_2.leaderboard.models.dto.CreatePlayerDto;
import com.leaderboard_2.leaderboard.models.dto.PlayerDto;
import com.leaderboard_2.leaderboard.models.dto.ShowProfileDto;
import com.leaderboard_2.leaderboard.repository.PlayerRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlayerService {

        private final PlayerRepo playerRepo;
        private final PlayerConverter pLayerConverter;

        public PlayerDto createPlayer(CreatePlayerDto createPlayerDto){
            Player player = new Player();
            player.setName(createPlayerDto.getName());
            player.setCountry(createPlayerDto.getCountry());
            Player createdPlayer = playerRepo.save(player);
            return pLayerConverter.apply(createdPlayer);
        }

        public PlayerDto showProfile(ShowProfileDto showProfileDto){
            UUID selectedMemberUid = UUID.fromString(showProfileDto.getUuid());
            Player showedPlayer = playerRepo.getOne(selectedMemberUid);
            return pLayerConverter.show(showedPlayer);
        }

    @Query(value = "SELECT uuid FROM leaderboard d where d.country = ?1" , nativeQuery = true)
    public PlayerDto showPlayerByCountry (String country){

        return null;
    }

}
