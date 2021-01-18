package com.leaderboard_2.leaderboard.repository;


import java.util.List;
import java.util.UUID;
import com.leaderboard_2.leaderboard.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PlayerRepo extends JpaRepository<Player, UUID> {
                  Player findByUid(String uid);
                    int countAllBy();

                  @Query(value="select e.uid, e.name, s.score, e.rank from player e, score s where e.uid = ?1 and e.score_id = s.score_seq", nativeQuery=true)
                public Player findByPlayerUid(String uid);


                @Query(value="select count(e.name) from player e where e.name = ?1", nativeQuery=true)
                public int checkName(String name);

    @Query(value="select count(e.name) from player e where e.name = ?1", nativeQuery=true)
    public int checkId(String name);



}
