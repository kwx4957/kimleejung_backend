package com.capstone.kimleejung.board.repository;


import com.capstone.kimleejung.board.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity,Long> {
    List<PostEntity> findAllByEnterpriseId(int enterpriseId);
}
