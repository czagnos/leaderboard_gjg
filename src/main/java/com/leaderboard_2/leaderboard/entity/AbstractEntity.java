package com.leaderboard_2.leaderboard.entity;


import lombok.Data;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import javax.persistence.*;


@MappedSuperclass
@Data
public class AbstractEntity {
            private ZonedDateTime updatedAt;


            @PrePersist
            private void preUpdate() {
                this.updatedAt = ZonedDateTime.now(ZoneId.of("UTC"));
            }


}
