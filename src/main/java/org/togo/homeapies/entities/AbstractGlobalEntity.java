package org.togo.homeapies.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.domain.Persistable;
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
