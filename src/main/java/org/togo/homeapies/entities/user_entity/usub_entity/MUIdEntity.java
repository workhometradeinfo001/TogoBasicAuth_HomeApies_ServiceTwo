package org.togo.homeapies.entities.user_entity.usub_entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.togo.homeapies.entities.AbstractGlobalEntity;
import org.togo.homeapies.entities.user_entity.UserEntity;

@Entity
@Table(name = "mongo_user_id")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MUIdEntity extends AbstractGlobalEntity<Long> {

    private String mongoUserId;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity userIdFromMongo;

}
