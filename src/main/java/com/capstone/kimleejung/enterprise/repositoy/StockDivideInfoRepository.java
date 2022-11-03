package com.capstone.kimleejung.enterprise.repositoy;

import com.capstone.kimleejung.enterprise.entity.StockDivideInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockDivideInfoRepository extends JpaRepository<StockDivideInfo,Long> {
    List<StockDivideInfo> findAllByStckIssuCmpyNm(String enterprise);
}
