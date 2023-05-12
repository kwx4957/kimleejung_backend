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
    private Long crno;
    private Integer basDt;
    private Double bpvtrCashDvdnTndnCtt;
    private Integer bpvtrOnskCashDvdnAmt;
    private Double bpvtrOnskCashDvdnBnfRt;
    private Double crtmCashDvdnTndnCtt;
    private Integer crtmOnskCashDvdnAmt;
    private Double crtmOnskCashDvdnBnfRt;
    private Double pvtrCashDvdnTndnCtt;
    private Integer pvtrOnskCashDvdnAmt;
    private Double pvtrOnskCashDvdnBnfRt;
}
