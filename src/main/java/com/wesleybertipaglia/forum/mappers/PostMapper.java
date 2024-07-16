package com.wesleybertipaglia.forum.mappers;

import com.wesleybertipaglia.forum.models.Post;
import com.wesleybertipaglia.forum.records.PostResponseDTO;

public class PostMapper {
    public static PostResponseDTO convertToDTO(Post post) {
        if (post == null) {
            throw new IllegalArgumentException("Entity is required");
        } else if (post.getId() == null) {
            throw new IllegalArgumentException("ID is required");
        } else if (post.getAuthor() == null) {
            throw new IllegalArgumentException("Author is required");
        } else if (post.getTitle() == null) {
            throw new IllegalArgumentException("Title is required");
        } else if (post.getContent() == null) {
            throw new IllegalArgumentException("Content is required");
        }

        return new PostResponseDTO(post.getId(), post.getAuthor().getId(), post.getTitle(), post.getContent());
    }
}