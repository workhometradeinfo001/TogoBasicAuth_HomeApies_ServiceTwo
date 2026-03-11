package org.togo.homeapies.repos.news_feed_repo.nfp_repo;

import org.springframework.stereotype.Repository;
import org.togo.homeapies.entities.news_feed_entity.nf_post_entity.nfp_sub_entity.NFPostTextEntity;
import org.togo.homeapies.repos.GlobalRepo;

@Repository
public interface NFPostTextRepo extends GlobalRepo<NFPostTextEntity, Long> {
}
