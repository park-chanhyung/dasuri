package com.project.dasuri.shop.repository;

import com.project.dasuri.shop.entity.ShopEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<ShopEntity,Long>{

    Page<ShopEntity> findAll(Pageable pageable);

    Page<ShopEntity> findByItemnameContaining(String keyword,Pageable page);




//    List<ShopEntity> findByPriceAndItemName(String price, String itemName);
////    Page<ShopEntity> findBySearchlist(Pageable pageable);


}
