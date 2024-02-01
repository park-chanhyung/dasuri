package com.project.dasuri.mypage.Repository;

import com.project.dasuri.member.entity.ProEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProMyPageRepository extends JpaRepository<ProEntity,String> {
    Optional<ProEntity> findByProId(String page);
}
