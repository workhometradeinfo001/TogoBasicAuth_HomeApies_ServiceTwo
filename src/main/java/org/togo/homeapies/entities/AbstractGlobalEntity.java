package org.togo.homeapies.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.domain.Persistable;
<<<<<<< HEAD
=======
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.togo.homeapies.custom_context.cc_user.CustomUserDetails;
import org.togo.homeapies.entities.user_entity.UserEntity;

import java.io.Serializable;
import java.time.Instant;
>>>>>>> d2b5cde (added craete post, exchange access token with refresh_token)
import java.time.LocalDateTime;

@MappedSuperclass
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractGlobalEntity<T> implements Persistable<T> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private T id;

    @Column(updatable = false)
    private LocalDateTime createAt;

    private LocalDateTime updateAt;

<<<<<<< HEAD
=======
    @CreatedBy
    private Long whoCreated;

    @LastModifiedBy
    private Long whoModify;

>>>>>>> d2b5cde (added craete post, exchange access token with refresh_token)
    @Transient
    public boolean isNewOrNot = true;

    @Override
    public boolean isNew(){
        return isNewOrNot;
    }

    @PostPersist
    @PostLoad
    void markNotNew(){
        this.isNewOrNot = false;
    }

}
