package com.capstone.kimleejung.comment.controller;

import com.capstone.kimleejung.comment.repository.CommentRepository;
import com.capstone.kimleejung.comment.model.CommentDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

    private final CommentRepository commentRepository;

    public CommentController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @PostMapping("/comment")
    public ResponseEntity<?>  createComment(@RequestBody CommentDto dto){
        commentRepository.save(dto.toEntity(dto));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{comment}")
    public ResponseEntity<?> deleteComment(@PathVariable("comment") long comment){
        commentRepository.deleteById(comment);
        return ResponseEntity.ok().build();
    }
}
