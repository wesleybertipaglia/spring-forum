package com.wesleybertipaglia.forum.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.wesleybertipaglia.forum.mappers.VoteMapper;
import com.wesleybertipaglia.forum.models.Vote;
import com.wesleybertipaglia.forum.models.Post;
import com.wesleybertipaglia.forum.models.User;
import com.wesleybertipaglia.forum.records.VoteRequestDTO;
import com.wesleybertipaglia.forum.records.VoteResponseDTO;
import com.wesleybertipaglia.forum.repositories.VoteRepository;
import com.wesleybertipaglia.forum.repositories.PostRepository;
import com.wesleybertipaglia.forum.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Transactional
    public Optional<VoteResponseDTO> create(VoteRequestDTO voteDTO) {
        User author = userRepository.findById(voteDTO.authorId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Post post = postRepository.findById(voteDTO.postId())
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));

        Vote vote = new Vote(post, author);
        return Optional.of(VoteMapper.convertToDTO(voteRepository.save(vote)));
    }

    @Transactional(readOnly = true)
    public Page<VoteResponseDTO> list(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return voteRepository.findAll(pageable).map(VoteMapper::convertToDTO);
    }

    @Transactional(readOnly = true)
    public Optional<VoteResponseDTO> get(UUID id) {
        Vote vote = voteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vote not found"));

        return Optional.of(VoteMapper.convertToDTO(vote));
    }

    @Transactional
    public void delete(UUID id) {
        Vote storedVote = voteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vote not found"));

        voteRepository.delete(storedVote);
    }
}
