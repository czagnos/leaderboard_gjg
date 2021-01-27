package com.leaderboard_2.leaderboard.manager;


import com.leaderboard_2.leaderboard.models.converter.CreatePlayerRequestConverter;
import com.leaderboard_2.leaderboard.models.converter.UuidConverter;
import com.leaderboard_2.leaderboard.models.dto.CreatePlayerDto;
import com.leaderboard_2.leaderboard.models.dto.PlayerDto;
import com.leaderboard_2.leaderboard.models.dto.GetProfileDto;
import com.leaderboard_2.leaderboard.models.request.CreatePlayerRequest;
import com.leaderboard_2.leaderboard.models.response.CreatePlayerResponse;
import com.leaderboard_2.leaderboard.models.response.GetProfileResponse;
import com.leaderboard_2.leaderboard.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerManager {

    private final PlayerService playerService;
    private final CreatePlayerRequestConverter createPlayerRequestConverter;
    private final UuidConverter uuidConverter;

    /*
     * Convert request input to data transfer objects and return as response body.
     *
     *@param  createplayerrequest body
     *@return createplayerresponse body
     */
    public CreatePlayerResponse createPlayer(CreatePlayerRequest createPlayerRequest){
        CreatePlayerDto createPlayerDto = createPlayerRequestConverter.apply(createPlayerRequest);
        PlayerDto player = playerService.createPlayer(createPlayerDto);

        return CreatePlayerResponse.builder()
                .playerDto(player)
                .build();
    }
    /*
     *Convert request input to data transfer objects and return as response body.
     *
     *@param  uuid as string
     *@return showplayerresponse body
     */
    public GetProfileResponse getProfile(String uuid){
        if (uuid == null) throw new IllegalArgumentException("Invalid uuid.");
        GetProfileDto getProfileDto = uuidConverter.apply(uuid);
        PlayerDto player = playerService.getProfile(getProfileDto);

        return GetProfileResponse.builder()
                .playerDto(player)
                .build();
    }

}
