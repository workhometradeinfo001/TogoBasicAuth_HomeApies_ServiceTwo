package org.togo.homeapies.entities.user_entity.usub_entity;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.togo.homeapies.entities.AbstractGlobalEntity;
import org.togo.homeapies.entities.AbstractUserEntity;
import org.togo.homeapies.entities.user_entity.UserEntity;

@Entity
@Table(name = "user_fullname_table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FullNameEntity extends AbstractUserEntity<Long> {

    private String firstName;
    private String lastName;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity userFullName;
}
