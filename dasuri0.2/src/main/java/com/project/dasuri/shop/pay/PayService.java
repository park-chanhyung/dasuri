package com.project.dasuri.shop.pay;


import com.project.dasuri.member.repository.UserRepository;
import com.project.dasuri.shop.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor

public class PayService {
    private final PayRepository payRepository;
    private final UserRepository userRepository;
    private final ShopRepository shopRepository;
    public PayEntity savePay(PayForm payForm) {
        // UserEntity와 ShopEntity를 불러옵니다.

        // PayEntity를 생성하고 데이터베이스에 저장합니다.
        PayEntity payEntity = new PayEntity();
        payEntity.setUserId(payEntity.getUserId());
        payEntity.setPayusername(payForm.getPayusername());
        payEntity.setUserId(payForm.getUserId());
        payEntity.setPrice(payForm.getPrice());
        payEntity.setItemname(payForm.getItemname());
        payEntity.setPayrequest(payForm.getPayrequest());
        payEntity.setUserPostcode(payForm.getUserPostcode());
        payEntity.setUserAddress(payForm.getUserAddress());
        payEntity.setUserDetailaddress(payForm.getUserDetailaddress());
        payEntity.setUserExtraaddress(payForm.getUserExtraaddress());
        payEntity.setNumber(payForm.getNumber());
        payEntity.setPayday(LocalDateTime.now());
        payEntity.setPaycheck(payForm.getPaycheck());

        System.out.println("페이체크#!@#!@"+payForm.getPaycheck());
        return payRepository.save(payEntity);
    }


    public List<PayEntity> getpaylist(){
        return this.payRepository.findAll();
    }



    public void savePay(PayEntity payEntity) {
        payRepository.save(payEntity);
    }
}
