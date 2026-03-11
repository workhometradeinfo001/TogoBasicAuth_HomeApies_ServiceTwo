package org.togo.homeapies.d_t_o_s.news_feed_dto.nf_post_dto;

import lombok.Data;

@Data
public class NFPostDto {

    private String postText;
    private String imgUrl;
    private long userId;
}
