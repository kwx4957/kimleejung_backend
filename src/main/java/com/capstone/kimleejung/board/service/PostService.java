package com.capstone.kimleejung.board.service;

import com.capstone.kimleejung.board.entity.PostEntity;
import com.capstone.kimleejung.board.model.PostDto;
import com.capstone.kimleejung.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final Logger logger = LoggerFactory.getLogger(PostService.class);
    private final PostRepository postRepository;

    public List<PostEntity> readPostAll(int enterpriseId){
        return this.postRepository.findAllByEnterpriseId(enterpriseId);
    }

    public ResponseEntity<PostEntity> readPost(long postId){
        Optional<PostEntity> dto = this.postRepository.findById(postId);
        return dto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> savePost(int enterpriseId, PostDto dto){
        this.postRepository.save(dto.toEntity(dto,enterpriseId));
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> updatePost(int enterpriseId,long postId, PostDto dto){
        Optional<PostEntity> boardDto = this.postRepository.findById(postId);
        logger.info(boardDto.toString());
        if(boardDto.isPresent()) {
            PostEntity postDto= PostEntity.builder()
                    .title(dto.getTitle())
                    .enterpriseId(boardDto.get().getEnterpriseId())
                    .nickname(dto.getNickname())
                    .content(dto.getContent())
                    .id(boardDto.get().getId())
                    .build();
            logger.info(postDto.toString());
            this.postRepository.save(postDto);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<?> deletePost(int enterpriseId,long postId){
        this.postRepository.deleteById(postId);
        return ResponseEntity.ok().build();
    }
}
