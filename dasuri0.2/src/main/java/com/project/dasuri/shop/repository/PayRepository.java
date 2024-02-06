package com.project.dasuri.shop.repository;

import com.project.dasuri.shop.entity.PayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PayRepository extends JpaRepository<PayEntity,Long> {
    @Query("SELECT p FROM PayEntity p WHERE p.userId = :userId AND p.paycheck = 2")
    List<PayEntity> findByUserIdPayCheck(@Param("userId") String userId);

}
