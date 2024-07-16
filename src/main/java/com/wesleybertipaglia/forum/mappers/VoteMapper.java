package com.wesleybertipaglia.forum.mappers;

import com.wesleybertipaglia.forum.models.Vote;
import com.wesleybertipaglia.forum.records.VoteResponseDTO;

public class VoteMapper {
    public static VoteResponseDTO convertToDTO(Vote vote) {
        if (vote == null) {
            throw new IllegalArgumentException("Entity is required");
        } else if (vote.getId() == null) {
            throw new IllegalArgumentException("ID is required");
        } else if (vote.getAuthor() == null) {
            throw new IllegalArgumentException("Author is required");
        } else if (vote.getPost() == null) {
            throw new IllegalArgumentException("Post is required");
        }

        return new VoteResponseDTO(vote.getId(), vote.getAuthor().getId(), vote.getPost().getId());
    }
}