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
@Table(name = "news_feed_post_img")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class NFPostImgEntity extends AbstractGlobalEntity<Long> {

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] postImg;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private NFPostEntity nfPostImg;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "who_post", referencedColumnName = "id")
//    private UserEntity userEntity;

}
