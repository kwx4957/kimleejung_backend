package com.capstone.kimleejung.enterprise.repositoy;

import com.capstone.kimleejung.enterprise.entity.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {
}
