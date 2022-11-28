package com.capstone.kimleejung.user.repository;

import com.capstone.kimleejung.user.entity.UserKim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserKim,Long> {
    Optional<UserKim> findByEmail(String email);
    boolean existsByUsername(String username);

    UserKim findByUsername(String username);
}
