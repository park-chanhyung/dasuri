package com.project.dasuri.member.repository;

import com.project.dasuri.member.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    boolean existsByUserId(String userId);

    UserEntity findByUserId(String username);
//    UserEntity findByUserId(String userId);
}
