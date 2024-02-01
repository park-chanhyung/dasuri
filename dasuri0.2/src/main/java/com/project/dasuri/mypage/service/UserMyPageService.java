package com.project.dasuri.mypage.service;

import com.project.dasuri.member.dto.UserDTO;
import com.project.dasuri.member.entity.UserEntity;
import com.project.dasuri.mypage.Repository.UserMyPageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserMyPageService {
    private final UserMyPageRepository userMyPageRepository;
    public UserDTO findById(String userId) {// findById사용자 아이디에 해당하는 db 정보를 dto로 변환
        Optional<UserEntity> userEntityOptional = userMyPageRepository.findByUserId(userId);
//            if (userEntityOptional.isPresent()) {
//                return UserDTO.toUserDTO(userEntityOptional.get());
//            }else {
//                return null;
//            }
        return userEntityOptional.map(UserDTO::toUserDTO).orElse(null);
    }
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
