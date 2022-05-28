package com.capstone.kimleejung.board.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;

@Table(name = "Post")
@NoArgsConstructor
@Entity
@Data
public class PostEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String nickname;
    private String content;
    private int viwes;
    private int enterpriseId;

    @Builder
    public PostEntity(Long id,String title, String nickname, String content, int enterpriseId) {
        this.id = id;
        this.title = title;
        this.nickname = nickname;
        this.content = content;
        this.enterpriseId = enterpriseId;
    }
}
