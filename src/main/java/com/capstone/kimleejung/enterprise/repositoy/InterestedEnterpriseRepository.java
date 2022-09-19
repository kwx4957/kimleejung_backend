package com.capstone.kimleejung.enterprise.repositoy;

import com.capstone.kimleejung.enterprise.entity.InterestedEnterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterestedEnterpriseRepository extends JpaRepository<InterestedEnterprise, Long> {
    List<InterestedEnterprise> findAllByUserId(long userId);
    Boolean deleteByUserIdAndEnterpriseCode(long userId, long enterpriseCode);
}
