package com.wesleybertipaglia.forum.records;

import java.util.UUID;

public record UserResponseDTO(UUID id, String name, String email) {
}