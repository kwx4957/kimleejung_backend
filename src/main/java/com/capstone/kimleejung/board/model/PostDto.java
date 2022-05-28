package com.capstone.kimleejung.board.model;

import com.capstone.kimleejung.board.entity.PostEntity;
import lombok.Data;

@Data
public class PostDto {
    private String title;
    private String nickname;
    private String content;

    public PostEntity toEntity(PostDto dto,int enterpiseId){
        return PostEntity
                .builder()
                .title(dto.getTitle())
                .nickname(dto.getNickname())
                .content(dto.getContent())
                .enterpriseId(enterpiseId)
                .build();
    }
}
