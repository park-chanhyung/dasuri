package com.project.dasuri.admin.repository;

import com.project.dasuri.member.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

//  관리자 페이지에서 고객 테이블 조회 리포지터리
public interface AdminUserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByUserId(String userId);

//    특정 날짜에 가입한 회원 카운트
    long countBySignupDateBetween(LocalDateTime day1, LocalDateTime day2);

    //    특정 지역에서 사는 고객 카운트
    long countByUserAddressContaining(String loc);

}
