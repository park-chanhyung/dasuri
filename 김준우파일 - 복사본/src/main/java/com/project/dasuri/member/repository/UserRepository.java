package com.project.dasuri.member.repository;

import com.project.dasuri.member.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
//    Optional<UserEntity> findByUserId(String userId);
}
