package com.project.dasuri.member.repository;

import com.project.dasuri.member.entity.ProEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProRepository extends JpaRepository<ProEntity,Integer> {

    boolean existsByProId(String userId);

    ProEntity findByProId(String username);

    boolean existsByProNickname(String userNickname);
    List<ProEntity> findByProNameAndBirth(String name, String birth);
}
