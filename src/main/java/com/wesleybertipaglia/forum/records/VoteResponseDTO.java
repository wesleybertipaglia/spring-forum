package com.wesleybertipaglia.forum.records;

import java.util.UUID;

public record VoteResponseDTO(UUID id, UUID user_id, UUID post_id) {
}