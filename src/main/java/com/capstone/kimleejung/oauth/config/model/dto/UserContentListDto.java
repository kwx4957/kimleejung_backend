package com.capstone.kimleejung.oauth.config.model.dto;

import com.capstone.kimleejung.oauth.config.model.Content;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserContentListDto {
    private Long userContentIndex;
    private Long userCode;
    private Long contentCode;

    private Content content;

    @Builder
    public UserContentListDto(Long userContentIndex, Long userCode, Long contentCode, Content content) {
        this.userContentIndex = userContentIndex;
        this.userCode = userCode;
        this.contentCode = contentCode;
        this.content = content;
    }
}
