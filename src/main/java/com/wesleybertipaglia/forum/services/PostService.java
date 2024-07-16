package com.wesleybertipaglia.forum.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.wesleybertipaglia.forum.mappers.PostMapper;
import com.wesleybertipaglia.forum.models.Post;
import com.wesleybertipaglia.forum.models.User;
import com.wesleybertipaglia.forum.records.PostRequestDTO;
import com.wesleybertipaglia.forum.records.PostResponseDTO;
import com.wesleybertipaglia.forum.repositories.PostRepository;
import com.wesleybertipaglia.forum.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Optional<PostResponseDTO> create(PostRequestDTO postDTO) {
        User author = userRepository.findById(postDTO.authorId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Post post = new Post(author, postDTO.title(), postDTO.content());
        return Optional.of(PostMapper.convertToDTO(postRepository.save(post)));
    }

    @Transactional(readOnly = true)
    public Page<PostResponseDTO> list(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return postRepository.findAll(pageable).map(PostMapper::convertToDTO);
    }

    @Transactional(readOnly = true)
    public Optional<PostResponseDTO> get(UUID id) {
        if (!postRepository.existsById(id)) {
            throw new EntityNotFoundException("Post not found");
        }

        return Optional.of(PostMapper.convertToDTO(postRepository.findById(id).get()));
    }

    @Transactional
    public Optional<PostResponseDTO> update(UUID id, PostRequestDTO postDTO) {
        Post storedPost = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));

        storedPost.setTitle(postDTO.title());
        storedPost.setContent(postDTO.content());

        return Optional.of(PostMapper.convertToDTO(postRepository.save(storedPost)));
    }

    @Transactional
    public void delete(UUID id) {
        Post storedPost = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));

        postRepository.delete(storedPost);
    }
}
