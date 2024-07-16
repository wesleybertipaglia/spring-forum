package com.wesleybertipaglia.forum.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import com.wesleybertipaglia.forum.models.Post;

public interface PostRepository extends JpaRepository<Post, UUID> {
}