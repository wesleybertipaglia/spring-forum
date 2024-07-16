package com.wesleybertipaglia.forum.records;

import java.util.UUID;

public record VoteRequestDTO(UUID authorId, UUID postId) {
}