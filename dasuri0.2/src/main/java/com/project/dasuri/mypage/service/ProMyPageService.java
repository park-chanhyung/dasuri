package com.project.dasuri.mypage.service;

import com.project.dasuri.member.dto.ProDTO;
import com.project.dasuri.member.entity.ProEntity;
import com.project.dasuri.mypage.Repository.ProMyPageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProMyPageService {
    private final ProMyPageRepository proMyPageRepository;
    public ProDTO findById(String proId) {
        Optional<ProEntity> proEntityOptional = proMyPageRepository.findByProId(proId);
//            if (proEntityOptional.isPresent()) {
//                return ProDTO.toProDTO(proEntityOptional.get());
//            }else {
//                return null;
//            }
        return proEntityOptional.map(ProDTO::toProDTO).orElse(null);
    }
//    // 새로운 메서드 추가: 데이터 업데이트
//    @Transactional  // 데이터베이스 트랜잭션 처리를 위해 추가
//    public void updateData(String proId, String newData) {
//        Optional<ProEntity> proEntityOptional = proMyPageRepository.findByProId(proId);
//
//        // 데이터가 존재하는지 확인
//        if (proEntityOptional.isPresent()) {
//            ProEntity proEntity = proEntityOptional.get();
//
//            // 실제로 업데이트할 데이터에 대한 로직을 여기에 추가
//            proEntity.setSomeData(newData);
//
//            // 업데이트된 엔터티를 저장
//            proMyPageRepository.save(proEntity);
//        }
//        // 데이터가 존재하지 않는 경우, 예외 처리 또는 다른 처리 방법을 선택할 수 있습니다.
//    }
}
