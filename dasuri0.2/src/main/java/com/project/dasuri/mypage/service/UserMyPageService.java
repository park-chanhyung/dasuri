package com.project.dasuri.mypage.service;

import com.project.dasuri.member.dto.ProDTO;
import com.project.dasuri.member.dto.UserDTO;
import com.project.dasuri.member.entity.ProEntity;
import com.project.dasuri.member.entity.UserEntity;
import com.project.dasuri.member.repository.UserRepository;
import com.project.dasuri.mypage.Repository.UserMyPageRepository;
import com.project.dasuri.mypage.Repository.UserUpPageRepository;
import com.project.dasuri.mypage.entity.UserPageEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserMyPageService {
    private final UserMyPageRepository userMyPageRepository;
    private final UserRepository userRepository;
//    public UserDTO findById(String userId) {// findById사용자 아이디에 해당하는 db 정보를 dto로 변환
//        Optional<UserEntity> userEntityOptional = userMyPageRepository.findByUserId(userId);
////            if (userEntityOptional.isPresent()) {
////                return UserDTO.toUserDTO(userEntityOptional.get());
////            }else {
////                return null;
////            }
//        return userEntityOptional.map(UserDTO::toUserDTO).orElse(null);
//    }

    @Transactional
    public void update(UserDTO userDTO, MultipartFile file) throws IOException {
        System.out.println(" 유저 이미지 수정 메소드");
        UserEntity userEntity = UserEntity.toUserEntity(userDTO);

        if (file != null && !file.isEmpty()) {
//        파일경로 변수
            String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\pro_files";

//        파일명 변수
            UUID uuid = UUID.randomUUID();
            String fileName = uuid + "_" + file.getOriginalFilename();

//        파일 객체 생성
            File saveFile = new File(projectPath, fileName);

//        생성한 파일 객체에 첨부파일 넣기
            file.transferTo(saveFile);

//        엔티티에 파일명 + 파일 경로 저장
            userEntity.setFilename(fileName);
            userEntity.setFilePath("/pro_files/" + fileName);
            userEntity.setProfileImagePath("/pro_files/" + fileName);
        }
        userRepository.save(userEntity);
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
