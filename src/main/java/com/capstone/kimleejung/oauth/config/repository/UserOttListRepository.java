package com.capstone.kimleejung.oauth.config.repository;

import com.capstone.kimleejung.oauth.config.model.UserOttList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserOttListRepository extends JpaRepository<UserOttList, Long> {

    List<UserOttList> findByUserCode(Long userCode);

    UserOttList findByUserCodeAndOttName(Long userCode, String ottName);
}
