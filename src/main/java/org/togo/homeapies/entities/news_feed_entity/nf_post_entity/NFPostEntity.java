package org.togo.homeapies.entities.news_feed_entity.nf_post_entity;

import jakarta.persistence.*;
import lombok.*;
import org.togo.homeapies.entities.AbstractGlobalEntity;
import org.togo.homeapies.entities.news_feed_entity.nf_post_entity.nfp_sub_entity.*;
import org.togo.homeapies.entities.user_entity.UserEntity;
import java.util.List;

@Entity
@Table(name = "news_feed_data")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class NFPostEntity extends AbstractGlobalEntity<Long> {

    @OneToOne(mappedBy = "nfPostText", cascade = CascadeType.REMOVE)
    private NFPostTextEntity postTextEntity;
    @OneToOne(mappedBy = "nfPostImg", cascade = CascadeType.REMOVE)
    private NFPostImgEntity postImgEntity;
    @OneToMany(mappedBy = "nfPostLike", cascade = CascadeType.REMOVE)
    private List<NFPLikeEntity> pLikeEntity;
    @OneToMany(mappedBy = "nfComment", cascade = CascadeType.REMOVE)
    private List<NFPCommentEntity> pCommentEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity userEntity;

}
