package com.project.dasuri.member.repository;

import com.project.dasuri.member.entity.ProEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProRepository extends JpaRepository<ProEntity,Integer> {

    boolean existsByProId(String userId);
}
