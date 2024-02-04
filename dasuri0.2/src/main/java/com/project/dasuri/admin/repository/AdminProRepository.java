package com.project.dasuri.admin.repository;

import com.project.dasuri.member.entity.ProEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

//  관리자 페이지에서 기사 테이블 조회 리포지터리
public interface AdminProRepository extends JpaRepository<ProEntity,Integer> {
    Optional<ProEntity> findByProId(String proId);

    //    특정 날짜에 가입한 기사 카운트
    long countBySignupDateBetween(LocalDateTime day1, LocalDateTime day2);

//    특정 지역에서 활동하는 기사 카운트
    long countByProLegionsContaining(String loc);
}
