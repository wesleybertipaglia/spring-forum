package com.wesleybertipaglia.forum.records;

import java.util.UUID;

public record VoteRequestDTO(UUID user_id, UUID post_id) {
}