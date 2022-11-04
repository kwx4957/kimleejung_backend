package com.capstone.kimleejung.enterprise.repositoy;

import com.capstone.kimleejung.enterprise.entity.StockSecuritiesInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockSecuritiesInfoRepository extends JpaRepository<StockSecuritiesInfo, Long> {
}
