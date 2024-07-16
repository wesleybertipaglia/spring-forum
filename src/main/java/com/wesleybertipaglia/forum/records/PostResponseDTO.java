package com.wesleybertipaglia.forum.records;

import java.util.UUID;

public record PostResponseDTO(UUID id, UUID user_id, String title, String content) {
}