package com.project.dasuri.member.repository;

import com.project.dasuri.member.entity.ProEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProRepository extends JpaRepository<ProEntity,Integer> {

    boolean existsByProId(String userId);

    ProEntity findByProId(String username);

//    Optional<ProEntity> findByProId(String username);
//    Optional<ProEntity> findByNum(Integer num);

    boolean existsByProNickname(String userNickname);
    List<ProEntity> findByProNameAndBirth(String name, String birth);
}
