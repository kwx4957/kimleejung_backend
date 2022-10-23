package com.capstone.kimleejung.enterprise.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
@Getter
public class StockDivideInfo {
    // https://www.data.go.kr/tcs/dss/selectApiDataDetailView.do?publicDataPk=15043284
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int basDt;
    private int crno;
    private String stckIssuCmpyNm;
    private int dvdnBasDt;
    private int cashDvdnPayDt;
    private int stckHndvDt;
    private int stckDvdnRcd;
    private String stckDvdnRcdNm;
    private int stckGenrDvdnAmt;
    private int stckGrdnDvdnAmt;
    private int stckGenrCashDvdnRt;
    private int stckGenrDvdnRt;
    private int cashGrdnDvdnRt;
    private int stckGrdnDvdnRt;
    private int stckParPrc;
    private int stckStacMd;

}
