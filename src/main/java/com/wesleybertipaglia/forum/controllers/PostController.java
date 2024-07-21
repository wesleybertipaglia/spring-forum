package com.wesleybertipaglia.forum.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wesleybertipaglia.forum.records.PostRequestDTO;
import com.wesleybertipaglia.forum.records.PostResponseDTO;
import com.wesleybertipaglia.forum.services.PostService;

import java.util.UUID;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<PostResponseDTO> create(@RequestBody PostRequestDTO post) {
        return ResponseEntity.of(postService.create(post));
    }

    @GetMapping
    public ResponseEntity<Page<PostResponseDTO>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(postService.list(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDTO> get(@PathVariable UUID id) {
        return ResponseEntity.of(postService.get(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponseDTO> update(@PathVariable UUID id, @RequestBody PostRequestDTO post) {
        return ResponseEntity.of(postService.update(id, post));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
