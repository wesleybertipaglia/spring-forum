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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wesleybertipaglia.forum.records.CommentRequestDTO;
import com.wesleybertipaglia.forum.records.CommentResponseDTO;
import com.wesleybertipaglia.forum.services.CommentService;

import java.util.UUID;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponseDTO> create(@RequestBody CommentRequestDTO comment) {
        return ResponseEntity.of(commentService.create(comment));
    }

    @GetMapping
    public ResponseEntity<Page<CommentResponseDTO>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(commentService.list(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponseDTO> get(@PathVariable UUID id) {
        return ResponseEntity.of(commentService.get(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentResponseDTO> update(@PathVariable UUID id, @RequestBody CommentRequestDTO comment) {
        return ResponseEntity.of(commentService.update(id, comment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        commentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
