package org.togo.homeapies.records.post_record;

import java.util.List;

public record ScrollResponse<T>(
        List<T> content,
        String nextToken,
        boolean hasNext
) {
}
