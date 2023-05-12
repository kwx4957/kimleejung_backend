package com.capstone.kimleejung.enterprise.repositoy;

import com.capstone.kimleejung.enterprise.entity.DiscInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscInfoRepository extends JpaRepository<DiscInfo, Long> {
    DiscInfo findTop1ByCrno(Long crno);

    DiscInfo findTop1ByCrnoOrderByBasDtDesc(Long crno);
}
