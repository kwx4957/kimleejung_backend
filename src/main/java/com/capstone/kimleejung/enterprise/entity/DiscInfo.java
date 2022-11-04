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
public class DiscInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Integer basDt;
    private double bpvtrCashDvdnTndnCtt;
    private int bpvtrOnskCashDvdnAmt;
    private double bpvtrOnskCashDvdnBnfRt;
    private double crtmCashDvdnTndnCtt;
    private int crtmOnskCashDvdnAmt;
    private double crtmOnskCashDvdnBnfRt;
    private double pvtrCashDvdnTndnCtt;
    private int pvtrOnskCashDvdnAmt;
    private double pvtrOnskCashDvdnBnfRt;
}
