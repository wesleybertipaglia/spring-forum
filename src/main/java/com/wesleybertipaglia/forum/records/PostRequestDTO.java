package com.wesleybertipaglia.forum.records;

import java.util.UUID;

public record PostRequestDTO(UUID authorId, String title, String content) {
}