package com.capstone.kimleejung.enterprise.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
@Getter
public class StockDivideInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

}
