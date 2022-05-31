package com.capstone.kimleejung.comment.entity;

import com.capstone.kimleejung.board.entity.PostEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "comment")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nickname;

    private String content;

    @ManyToOne(targetEntity = PostEntity.class)
    @JoinColumn(name = "post_Id")
    private PostEntity postEntity;

    @Builder
    public Comment(String nickname, String content, PostEntity postEntity) {
        this.nickname = nickname;
        this.content = content;
        this.postEntity = postEntity;
    }
}
