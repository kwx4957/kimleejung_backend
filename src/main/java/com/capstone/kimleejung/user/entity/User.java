package com.capstone.kimleejung.user.entity;

import com.capstone.kimleejung.config.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String email, Role role) {
        this.email = email;
        this.role = role;
    }

    public User update(String email){
        this.email = email;
        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }
}
