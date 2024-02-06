package com.project.dasuri.shop.service;

import com.project.dasuri.shop.DataNotFoundException;
import com.project.dasuri.shop.entity.ShopEntity;
import com.project.dasuri.shop.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShopService {
    private final ShopRepository shopRepository;

    public void create(Long id, String itemname, String price, String seller, String deliveryinfo,
                       String shortinfo, String iteminfo,
                       MultipartFile file
    ) throws IOException {
        ShopEntity s = new ShopEntity();
        s.setId(id);
        s.setItemname(itemname);
        s.setPrice(price);
        s.setSeller(seller);
        s.setDeliveryinfo(deliveryinfo);
        s.setRegtime(LocalDateTime.now());
        s.setShortinfo(shortinfo);
        s.setIteminfo(iteminfo);

        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

        UUID uuid = UUID.randomUUID();

        String fileName = uuid + "_" + file.getOriginalFilename();
        File saveFile = new File(projectPath, fileName);

        file.transferTo(saveFile);

        s.setFilename(fileName);
        s.setFilePath("/files/" + fileName);
        this.shopRepository.save(s);
    }

    public Page<ShopEntity> itemlist(int page) {
        Pageable pageable= PageRequest.of(page,12);
        return this.shopRepository.findAll(pageable);
    }

    public ShopEntity getItem(Long id) {
        Optional<ShopEntity> shopEntity = this.shopRepository.findById(id);
        if (shopEntity.isPresent()) {
            return shopEntity.get();
        } else {
            throw new DataNotFoundException("item not found!!!");
        }

    }

    public void modify(ShopEntity item,String itemname, String price, String seller, String deliveryinfo,
                       String shortinfo, String iteminfo,
                       MultipartFile file
    ) throws IOException {
        item.setItemname(itemname);
        item.setPrice(price);
        item.setSeller(seller);
        item.setDeliveryinfo(deliveryinfo);
        item.setRegtime(LocalDateTime.now());
        item.setShortinfo(shortinfo);
        item.setIteminfo(iteminfo);

        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

        UUID uuid = UUID.randomUUID();

        String fileName = uuid + "_" + file.getOriginalFilename();
        File saveFile = new File(projectPath, fileName);

        file.transferTo(saveFile);

        item.setFilename(fileName);
        item.setFilePath("/files/" + fileName);
        this.shopRepository.save(item);
    }
    public void delete(ShopEntity item){
        this.shopRepository.delete(item);
    }

//    public Page<ShopEntity> searchItem(int page) {
//        Pageable pageable= PageRequest.of(page,12);
//        return shopRepository.findByItemnameContaining(pageable);
//    }
    public Page<ShopEntity> searchItem(String keyword, int page) {
        Pageable pageable= PageRequest.of(page,12);
        return this.shopRepository.findByItemnameContaining(keyword, pageable);
    }

    public List<ShopEntity> getlist() {
        return this.shopRepository.findAll();
    }

}
