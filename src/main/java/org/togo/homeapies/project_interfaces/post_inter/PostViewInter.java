package org.togo.homeapies.project_interfaces.post_inter;

import org.springframework.beans.factory.annotation.Value;
import java.time.Instant;

public interface PostViewInter {
    Long getId();
    Long getWhoCreated();
    @Value("#{target.postTextEntity?.postText}")
    String getPostText();
    @Value("#{target.postImgEntity?.postImg}")
    String getPostImg();
    @Value("#{target.userEntity?.fullName?.firstName}")
    String getFirstName();
    @Value("#{target.userEntity?.fullName?.lastName}")
    String getLastName();
    Instant getCreateAt();
}
