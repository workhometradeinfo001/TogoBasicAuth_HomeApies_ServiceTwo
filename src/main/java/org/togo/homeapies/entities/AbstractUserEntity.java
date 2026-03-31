package org.togo.homeapies.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.jspecify.annotations.Nullable;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@SuperBuilder
@EntityListeners(AuditingEntityListener.class)
public class AbstractUserEntity<ID extends Serializable> implements Persistable<ID> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private ID id;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private Instant createAt;

    @LastModifiedDate
    private LocalDateTime updateAt;

    @CreatedBy
    @Column(columnDefinition = "BIGINT")
    protected Long createBy;

    @LastModifiedBy
    @Column(columnDefinition = "BIGINT")
    protected Long updateBy;

    @PostPersist
    public void setCreateBy(){
        createBy = (Long) this.id;
        updateBy = (Long) this.id;
    }
    @PostUpdate
    public void setUpdateBy(){
        updateBy = (Long) this.id;
    }
    @Transient
    @Override
    public @Nullable ID getId() {
        return null;
    }

    @Transient
    @Override
    public boolean isNew() {
        return false;
    }
}
