package com.leaderboard_2.leaderboard.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "score")
public class Score extends AbstractEntity {
    @Id
    @GeneratedValue
    private Long score_seq;

    @Column(name = "user_id")
    private String userId;

    private Double score;

    private String country;

}
