package com.project.dasuri.shop.repository;

import com.project.dasuri.shop.entity.ReviewEntity;
import com.project.dasuri.shop.entity.ShopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity,Long> {
    List<ReviewEntity> findByShopEntity(ShopEntity shopEntity);


    @Query("SELECT AVG(CAST(r.star as double)) FROM ReviewEntity r WHERE r.shopEntity = :shopEntity")
    Double starAvg(@Param("shopEntity") ShopEntity shopEntity);


}