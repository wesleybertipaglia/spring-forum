package com.wesleybertipaglia.forum.records;

import java.util.UUID;

public record PostRequestDTO(UUID user_id, String title, String content) {
}