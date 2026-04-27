package org.togo.homeapies.records.post_record;

import java.time.Instant;
public record PostRecordDto(
        Long id,
        Long creator,
        String postText,
        String postImage,
        String firstName,
        String lastName,
        Instant createTime
) {
}
