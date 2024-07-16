package com.wesleybertipaglia.forum.records;

import java.util.UUID;

public record PostResponseDTO(UUID id, UUID authorId, String title, String content) {
}