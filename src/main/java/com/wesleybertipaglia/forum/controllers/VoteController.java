package com.wesleybertipaglia.forum.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.wesleybertipaglia.forum.records.VoteRequestDTO;
import com.wesleybertipaglia.forum.records.VoteResponseDTO;
import com.wesleybertipaglia.forum.services.VoteService;

import java.util.UUID;

public class VoteController {
    @Autowired
    private VoteService voteService;

    @PostMapping
    public ResponseEntity<VoteResponseDTO> create(@RequestBody VoteRequestDTO vote) {
        return ResponseEntity.of(voteService.create(vote));
    }

    @GetMapping
    public ResponseEntity<Page<VoteResponseDTO>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(voteService.list(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VoteResponseDTO> get(@PathVariable UUID id) {
        return ResponseEntity.of(voteService.get(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        voteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
