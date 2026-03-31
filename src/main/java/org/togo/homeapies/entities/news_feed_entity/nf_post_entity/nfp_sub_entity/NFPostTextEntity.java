package org.togo.homeapies.entities.news_feed_entity.nf_post_entity.nfp_sub_entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.togo.homeapies.entities.AbstractGlobalEntity;
import org.togo.homeapies.entities.news_feed_entity.nf_post_entity.NFPostEntity;

@Entity
@Table(name = "news_feed_post_text")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class NFPostTextEntity extends AbstractGlobalEntity<Long> {

    private String postText;

    @OneToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private NFPostEntity nfPostText;

}
