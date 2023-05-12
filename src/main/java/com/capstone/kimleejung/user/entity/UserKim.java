package com.capstone.kimleejung.user.entity;

import com.capstone.kimleejung.config.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "User")
@Builder
@AllArgsConstructor
public class UserKim extends BaseTimeEntity {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id; // 유저에게 고유하게 부여되는 id.

    @Column(nullable = false)
    private String username; // 아이디로 사용할 유저네임. 이메일일 수도 그냥 문자열일 수도 있다.

    private String email;

    private String password; // 패스워드.

    private String role; // 유저의 롤.

    private String authProvider; // example : facebookrname;
}

