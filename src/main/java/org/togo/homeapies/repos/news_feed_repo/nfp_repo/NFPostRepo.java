package org.togo.homeapies.repos.news_feed_repo.nfp_repo;

import org.springframework.data.domain.Limit;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.data.domain.Window;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.togo.homeapies.entities.news_feed_entity.nf_post_entity.NFPostEntity;
import org.togo.homeapies.project_interfaces.post_inter.PostViewInter;
import org.togo.homeapies.repos.GlobalRepo;

import java.util.List;


@Repository
public interface NFPostRepo extends GlobalRepo<NFPostEntity, Long> {

    @Transactional
    @Query(value = "CALL GetAllPost(:pageLimit, :pageOffset)", nativeQuery = true)
    List<PostViewInter> findAllPosts(@Param("pageLimit") int pageLimit, @Param("pageOffset") int pageOffset);

    Window<PostViewInter> findByOrderByCreateAtDesc(ScrollPosition position, Limit limit);

}
