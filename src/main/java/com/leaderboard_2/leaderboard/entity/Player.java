package com.leaderboard_2.leaderboard.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "player")
public class Player extends AbstractEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String uid;

    private String name;

    private String country;

    private int rank;

    @OneToOne
    @JoinColumn(name = "score_id", referencedColumnName = "score_seq")
    private Score score;

}
