package com.project.dasuri.admin.service;

import com.project.dasuri.admin.repository.AdminProRepository;
import com.project.dasuri.member.dto.ProDTO;
import com.project.dasuri.member.entity.ProEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Admin_ProService {
    private final AdminProRepository proRepository;

    //    기사리스트
    public List<ProDTO> findAll(){
        List<ProEntity> proEntities = proRepository.findAll();
        List<ProDTO> proDTOS = new ArrayList<>();
        for (ProEntity proEntity : proEntities){
            proDTOS.add(ProDTO.toProDTO(proEntity));
        }
        return proDTOS;
    }

    //    기사정보 조회
    public ProDTO findByProId(String proId){
        Optional<ProEntity> proEntity = proRepository.findByProId(proId);
        if (proEntity.isPresent()){
            return ProDTO.toProDTO(proEntity.get());
        }else {
            return null;
        }
    }

    //    기사 수
    public Long proCount(){
        return proRepository.count();
    }

    //    오늘부터 -6일 전까지 가입한 기사 수
    public List<Long> userSignupDateCount(){
        LocalDateTime now = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);

        LocalDateTime today = now;
        LocalDateTime day1 = now.minusDays(1);
        LocalDateTime day2 = now.minusDays(2);
        LocalDateTime day3 = now.minusDays(3);
        LocalDateTime day4 = now.minusDays(4);
        LocalDateTime day5 = now.minusDays(5);
        LocalDateTime day6 = now.minusDays(6);

        List<Long> counts = new ArrayList<>();

        counts.add(proRepository.countBySignupDateBetween(now, now.plusDays(1))); // 오늘
        counts.add(proRepository.countBySignupDateBetween(day1, day1.plusDays(1))); // 어제
        counts.add(proRepository.countBySignupDateBetween(day2, day2.plusDays(1)));
        counts.add(proRepository.countBySignupDateBetween(day3, day3.plusDays(1)));
        counts.add(proRepository.countBySignupDateBetween(day4, day4.plusDays(1)));
        counts.add(proRepository.countBySignupDateBetween(day5, day5.plusDays(1)));
        counts.add(proRepository.countBySignupDateBetween(day6, day6.plusDays(1)));

        return counts;
    }

}
