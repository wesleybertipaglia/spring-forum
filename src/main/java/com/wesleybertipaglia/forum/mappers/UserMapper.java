package com.wesleybertipaglia.forum.mappers;

import com.wesleybertipaglia.forum.models.User;
import com.wesleybertipaglia.forum.records.UserResponseDTO;

public class UserMapper {
    public static UserResponseDTO convertToDTO(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Entity is required");
        } else if (user.getId() == null) {
            throw new IllegalArgumentException("ID is required");
        } else if (user.getName() == null) {
            throw new IllegalArgumentException("Name is required");
        } else if (user.getEmail() == null) {
            throw new IllegalArgumentException("Email is required");
        }

        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail());
    }

    public static User convertToEntity(UserResponseDTO userDTO) {
        if (userDTO == null) {
            throw new IllegalArgumentException("Entity is required");
        } else if (userDTO.id() == null) {
            throw new IllegalArgumentException("ID is required");
        } else if (userDTO.name() == null) {
            throw new IllegalArgumentException("Name is required");
        } else if (userDTO.email() == null) {
            throw new IllegalArgumentException("Email is required");
        }

        return new User(userDTO.id(), userDTO.name(), userDTO.email());
    }
}