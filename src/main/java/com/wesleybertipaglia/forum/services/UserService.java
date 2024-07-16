package com.wesleybertipaglia.forum.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.wesleybertipaglia.forum.mappers.UserMapper;
import com.wesleybertipaglia.forum.models.User;
import com.wesleybertipaglia.forum.records.UserRequestDTO;
import com.wesleybertipaglia.forum.records.UserResponseDTO;
import com.wesleybertipaglia.forum.repositories.UserRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Optional<UserResponseDTO> create(UserRequestDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.email())) {
            throw new EntityExistsException("Email already in use");
        }

        User user = new User(userDTO.name(), userDTO.email(), userDTO.password());
        return Optional.of(UserMapper.convertToDTO(userRepository.save(user)));
    }

    @Transactional(readOnly = true)
    public Page<UserResponseDTO> list(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable).map(UserMapper::convertToDTO);
    }

    @Transactional(readOnly = true)
    public Optional<UserResponseDTO> get(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found");
        }

        return Optional.of(UserMapper.convertToDTO(userRepository.findById(id).get()));
    }

    @Transactional
    public Optional<UserResponseDTO> update(UUID id, UserRequestDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.email())) {
            throw new EntityExistsException("Email already in use");
        }

        User storedUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        storedUser.setName(userDTO.name());
        storedUser.setEmail(userDTO.email());
        storedUser.setPassword(userDTO.password());

        return Optional.of(UserMapper.convertToDTO(userRepository.save(storedUser)));
    }

    @Transactional
    public void delete(UUID id) {
        User storedUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        userRepository.delete(storedUser);
    }
}
