package com.wesleybertipaglia.forum.records;

import java.util.UUID;

public record CommentRequestDTO(UUID user_id, UUID post_id, String content) {
}