package com.wesleybertipaglia.forum.records;

import java.util.UUID;

public record CommentResponseDTO(UUID id, UUID user_id, UUID post_id, String content) {
}