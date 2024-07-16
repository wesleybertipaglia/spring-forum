package com.wesleybertipaglia.forum.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import com.wesleybertipaglia.forum.models.Vote;

public interface VoteRepository extends JpaRepository<Vote, UUID> {
}