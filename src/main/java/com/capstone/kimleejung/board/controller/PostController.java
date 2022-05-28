package com.capstone.kimleejung.board.controller;

import com.capstone.kimleejung.board.entity.PostEntity;
import com.capstone.kimleejung.board.model.PostDto;
import com.capstone.kimleejung.board.repository.PostRepository;
import com.capstone.kimleejung.board.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/board/{enterprise}")
public class PostController {
    private final Logger logger = LoggerFactory.getLogger(PostController.class);
    private final PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    //게시판조회
    @GetMapping()
    public List<PostEntity> readPostAll(@PathVariable("enterprise") int enterpriseId){
        return this.postService.readPostAll(enterpriseId);

    }

    //게시글삽입
    @PostMapping()
    public ResponseEntity<?> createPost(@PathVariable("enterprise") int enterpriseId, @RequestBody PostDto dto){
        return this.postService.savePost(enterpriseId,dto);
    }

    //게시글조회
    @GetMapping("/{postId}")
    public ResponseEntity<PostEntity> readPost(@PathVariable("enterprise") int enterpriseId,@PathVariable("postId") Long postId){
        return this.postService.readPost(postId);
    }


    //게시글수정
    @PutMapping("{postId}")
    public ResponseEntity<?> updateBoard(
            @PathVariable("enterprise") int enterpriseId,
            @PathVariable("postId") Long postId,
            @RequestBody PostDto dto){

        return this.postService.updatePost(enterpriseId,postId,dto);
    }

    //게시글삭제
    @DeleteMapping("{postId}")
    public ResponseEntity<?> deleteBoard(@PathVariable("enterprise") int enterpriseId, @PathVariable("postId") Long postId){
        return this.postService.deletePost(enterpriseId,postId);
    }
}
