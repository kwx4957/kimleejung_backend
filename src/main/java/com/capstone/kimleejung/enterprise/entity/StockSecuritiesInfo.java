package com.capstone.kimleejung.enterprise.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockSecuritiesInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long basDt;
    private int clpr;

}
