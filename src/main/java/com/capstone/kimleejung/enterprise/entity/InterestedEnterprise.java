package com.capstone.kimleejung.enterprise.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
@Getter
public class InterestedEnterprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long userId;
    private long enterpriseCode;

    public InterestedEnterprise(long userId, long enterpriseCode) {
        this.userId = userId;
        this.enterpriseCode = enterpriseCode;
    }
}
