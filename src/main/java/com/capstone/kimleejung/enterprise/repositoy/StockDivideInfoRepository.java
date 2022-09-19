package com.capstone.kimleejung.enterprise.repositoy;

import com.capstone.kimleejung.enterprise.entity.StockDivideInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockDivideInfoRepository extends JpaRepository<StockDivideInfo,Long> {
}
