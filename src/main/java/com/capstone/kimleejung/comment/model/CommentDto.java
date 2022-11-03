package com.capstone.kimleejung.comment.model;

import com.capstone.kimleejung.board.entity.PostEntity;
import com.capstone.kimleejung.comment.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentDto {
    private String nickname;
    private String content;
    private long postId;

    public Comment toEntity(CommentDto dto){
       return Comment.builder()
               .content(dto.getContent())
               .nickname(dto.getNickname())
               .postEntity(
                       PostEntity
                            .builder()
                            .id(dto.getPostId())
                            .build())
               .build();
    }
}
