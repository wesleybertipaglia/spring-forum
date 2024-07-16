package com.wesleybertipaglia.forum.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.wesleybertipaglia.forum.mappers.CommentMapper;
import com.wesleybertipaglia.forum.models.Comment;
import com.wesleybertipaglia.forum.models.Post;
import com.wesleybertipaglia.forum.models.User;
import com.wesleybertipaglia.forum.records.CommentRequestDTO;
import com.wesleybertipaglia.forum.records.CommentResponseDTO;
import com.wesleybertipaglia.forum.repositories.CommentRepository;
import com.wesleybertipaglia.forum.repositories.PostRepository;
import com.wesleybertipaglia.forum.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Transactional
    public Optional<CommentResponseDTO> create(CommentRequestDTO commentDTO) {
        User author = userRepository.findById(commentDTO.authorId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Post post = postRepository.findById(commentDTO.postId())
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));

        Comment comment = new Comment(post, author, commentDTO.content());
        return Optional.of(CommentMapper.convertToDTO(commentRepository.save(comment)));
    }

    @Transactional(readOnly = true)
    public Page<CommentResponseDTO> list(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return commentRepository.findAll(pageable).map(CommentMapper::convertToDTO);
    }

    @Transactional(readOnly = true)
    public Optional<CommentResponseDTO> get(UUID id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));

        return Optional.of(CommentMapper.convertToDTO(comment));
    }

    @Transactional
    public Optional<CommentResponseDTO> update(UUID id, CommentRequestDTO commentDTO) {
        Comment storedComment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));

        storedComment.setContent(commentDTO.content());
        return Optional.of(CommentMapper.convertToDTO(commentRepository.save(storedComment)));
    }

    @Transactional
    public void delete(UUID id) {
        Comment storedComment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));

        commentRepository.delete(storedComment);
    }
}
