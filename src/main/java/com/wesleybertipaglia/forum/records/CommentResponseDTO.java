package com.wesleybertipaglia.forum.records;

import java.util.UUID;

public record CommentResponseDTO(UUID id, UUID authorId, UUID postId, String content) {
}