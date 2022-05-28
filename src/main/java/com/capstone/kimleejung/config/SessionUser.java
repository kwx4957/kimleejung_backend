package com.capstone.kimleejung.config;

import com.capstone.kimleejung.user.entity.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String email;

    public SessionUser(User user) {
        this.email = user.getEmail();
    }
}