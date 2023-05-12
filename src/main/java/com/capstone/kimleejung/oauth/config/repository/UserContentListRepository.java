package com.capstone.kimleejung.oauth.config.repository;

import com.capstone.kimleejung.oauth.config.model.UserContentList;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserContentListRepository extends JpaRepository<UserContentList, Long> {

    UserContentList findByUserCodeAndContentCode(Long userCode, Long contentCode);

    List<UserContentList> findByUserCode(Long userCode, Sort sort);
}
