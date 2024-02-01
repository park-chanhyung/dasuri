package com.project.dasuri.mypage.Repository;

import com.project.dasuri.member.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserMyPageRepository extends JpaRepository<UserEntity,String> {
    
//    사용자 아이에 해당하는 db 찾은후 반환
    Optional<UserEntity> findByUserId(String userId);
}
