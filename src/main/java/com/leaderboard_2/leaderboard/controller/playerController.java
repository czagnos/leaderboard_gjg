package com.leaderboard_2.leaderboard.controller;

import com.leaderboard_2.leaderboard.manager.PlayerManager;
import com.leaderboard_2.leaderboard.models.response.GetProfileResponse;
import com.leaderboard_2.leaderboard.service.PlayerService;
import org.springframework.web.bind.annotation.*;
import com.leaderboard_2.leaderboard.models.response.CreatePlayerResponse;
import com.leaderboard_2.leaderboard.models.request.CreatePlayerRequest;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class playerController {

    private final PlayerManager playerManager;
    private final PlayerService playerService;


    /*
    * Geting request of user creation
    *
    *@param  createplayerrequest body
    *@return createplayerresponse body
     */
    @PostMapping("/v1/user/create")
    public CreatePlayerResponse createPlayer(@RequestBody CreatePlayerRequest createPlayerRequest){
        return playerManager.createPlayer(createPlayerRequest);
    }

    /*
     *Geting request of user profile
     *
     *@param  uuid of player
     *@return createplayerresponse body
     */
    @GetMapping("/v1/user/{uuid}")
    public GetProfileResponse getProfile(@PathVariable String uuid){
        return playerManager.getProfile(uuid);
    }

    @GetMapping("/v1/{n}")
    public void createRandomUser(@PathVariable int n){
         playerService.randomUser(n);
    }

}
