package com.wesleybertipaglia.forum.records;

import java.util.UUID;

public record CommentRequestDTO(UUID authorOd, UUID postId, String content) {
}