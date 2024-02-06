package com.project.dasuri.admin.repository;

import com.project.dasuri.member.entity.ProEntity;
import com.project.dasuri.member.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

//  관리자 페이지에서 기사 테이블 조회 리포지터리
public interface AdminProRepository extends JpaRepository<ProEntity,Integer> {
    Optional<ProEntity> findByProId(String proId);

    //    특정 날짜에 가입한 기사 카운트
    long countBySignupDateBetween(LocalDateTime day1, LocalDateTime day2);

//    특정 지역에서 활동하는 기사 카운트
    long countByProLegionsContaining(String loc);

    //    기사 검색 페이징 (아이디,이름,닉네임,주소)
    Page<ProEntity> findByProIdContainingOrProNameContainingOrProNicknameContainingOrProLegionsContaining(String k1, String k2, String k3, String k4, Pageable pageable);

}
