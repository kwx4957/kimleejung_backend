package com.capstone.kimleejung.enterprise.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
@Getter
public class Enterprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String enterprise;
    private int enterpriseCode;

    public Enterprise(String enterprise, int enterpriseCode) {
        this.enterprise = enterprise;
        this.enterpriseCode = enterpriseCode;
    }
}
