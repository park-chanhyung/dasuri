package com.project.dasuri.admin.repository;

import com.project.dasuri.member.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

//  관리자 페이지에서 고객 테이블 조회 리포지터리
public interface AdminUserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByUserId(String userId);
}
