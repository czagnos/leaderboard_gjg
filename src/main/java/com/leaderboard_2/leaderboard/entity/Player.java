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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uid = UUID.randomUUID().toString();

    private String name;

    private String country;

    @OneToOne
    @JoinColumn(name = "score_id", referencedColumnName = "id")
    private Score score;

}
