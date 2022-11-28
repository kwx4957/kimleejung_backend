package com.capstone.kimleejung.oauth.config.repository;

import com.capstone.kimleejung.oauth.config.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// 기본적인 CRUD 함수를 가지고 있음
// JpaRepository를 상속했기 때문에 @Repository 어노테이션 불필요
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // JPA findBy 규칙
    // select * user_master where kakao_id = ?
    User findByKakaoEmail(String kakaoEmail);

    User findByUserCode(Long userCode);
}
