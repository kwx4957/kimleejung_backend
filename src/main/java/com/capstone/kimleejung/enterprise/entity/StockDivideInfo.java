package com.capstone.kimleejung.enterprise.entity;

import com.capstone.kimleejung.config.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
public class StockDivideInfo extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Integer basDt;
    private String crno;
    private String stckIssuCmpyNm;
    private Integer dvdnBasDt;
    private Integer cashDvdnPayDt;
    private Integer stckHndvDt;
    private Integer stckDvdnRcd;
    private String stckDvdnRcdNm;
    private Integer stckGenrDvdnAmt;
    private Double stckGenrCashDvdnRt;
    private Double stckGenrDvdnRt;
    private Double cashGrdnDvdnRt;
    private Integer stckGrdnDvdnRt;
    private Integer stckParPrc;
    private Integer stckStacMd;

}
