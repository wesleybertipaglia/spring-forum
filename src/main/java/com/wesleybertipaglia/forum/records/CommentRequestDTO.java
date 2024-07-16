package com.wesleybertipaglia.forum.records;

import java.util.UUID;

public record CommentRequestDTO(UUID authorId, UUID postId, String content) {
}