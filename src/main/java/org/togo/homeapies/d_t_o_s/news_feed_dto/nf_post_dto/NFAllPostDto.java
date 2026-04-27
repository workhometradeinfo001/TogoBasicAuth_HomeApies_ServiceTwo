package org.togo.homeapies.d_t_o_s.news_feed_dto.nf_post_dto;

import lombok.*;


@Getter @Setter
@AllArgsConstructor
public class NFAllPostDto {

    private Long postId;
    private String userFullName;
    private String postText;
    private String postImg;
    private String postDate;

}
