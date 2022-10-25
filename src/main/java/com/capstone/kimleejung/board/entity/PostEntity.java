package com.capstone.kimleejung.board.entity;

import com.capstone.kimleejung.comment.entity.Comment;
import com.capstone.kimleejung.config.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.util.List;

@Table(name = "Post")
@NoArgsConstructor
@Entity
@Getter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class PostEntity extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String nickname;
    private String content;
    private int viwes;
    private int enterpriseId;
    @OneToMany(mappedBy = "postEntity", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    @Builder
    public PostEntity(Long id,String title, String nickname, String content, int enterpriseId) {
        this.id = id;
        this.title = title;
        this.nickname = nickname;
        this.content = content;
        this.enterpriseId = enterpriseId;
    }
}
