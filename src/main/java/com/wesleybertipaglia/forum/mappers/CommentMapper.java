package com.wesleybertipaglia.forum.mappers;

import com.wesleybertipaglia.forum.models.Comment;
import com.wesleybertipaglia.forum.records.CommentResponseDTO;

public class CommentMapper {
    public static CommentResponseDTO convertToDTO(Comment comment) {
        if (comment == null) {
            throw new IllegalArgumentException("Entity is required");
        } else if (comment.getId() == null) {
            throw new IllegalArgumentException("ID is required");
        } else if (comment.getAuthor() == null) {
            throw new IllegalArgumentException("Author is required");
        } else if (comment.getPost() == null) {
            throw new IllegalArgumentException("Post is required");
        } else if (comment.getContent() == null) {
            throw new IllegalArgumentException("Content is required");
        }

        return new CommentResponseDTO(comment.getId(), comment.getAuthor().getId(), comment.getPost().getId(),
                comment.getContent());
    }
}