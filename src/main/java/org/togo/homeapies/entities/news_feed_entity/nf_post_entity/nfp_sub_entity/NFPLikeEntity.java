package org.togo.homeapies.entities.news_feed_entity.nf_post_entity.nfp_sub_entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.togo.homeapies.entities.AbstractGlobalEntity;
import org.togo.homeapies.entities.news_feed_entity.nf_post_entity.NFPostEntity;
import org.togo.homeapies.entities.user_entity.UserEntity;

@Entity
@Table(name = "news_feed_like",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"post_id", "who_liked"})
    }
)
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class NFPLikeEntity extends AbstractGlobalEntity<Long> {

    private boolean isLiked;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private NFPostEntity nfPostLike;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "who_liked", referencedColumnName = "id")
//    private UserEntity userEntity;
}
