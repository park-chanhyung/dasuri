package com.project.dasuri.shop;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopRepository extends JpaRepository<ShopEntity ,Long>{

    Page<ShopEntity> findAll(Pageable pageable);

    List<ShopEntity> findByItemnameContaining(String keyword);

//    List<ShopEntity> findByPriceAndItemName(String price, String itemName);
////    Page<ShopEntity> findBySearchlist(Pageable pageable);


}
