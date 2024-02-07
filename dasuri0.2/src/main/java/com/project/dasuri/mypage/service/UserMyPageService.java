package com.project.dasuri.mypage.service;

import com.project.dasuri.member.dto.ProDTO;
import com.project.dasuri.member.dto.UserDTO;
import com.project.dasuri.member.entity.ProEntity;
import com.project.dasuri.member.entity.UserEntity;
import com.project.dasuri.member.repository.UserRepository;
import com.project.dasuri.mypage.Repository.UserMyPageRepository;
import com.project.dasuri.mypage.Repository.UserUpPageRepository;
import com.project.dasuri.mypage.entity.UserPageEntity;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public boolean update(UserDTO userDTO, MultipartFile file) throws IOException {
//    public void update(ProDTO proDTO, MultipartFile file) throws IOException {
        // 기존 정보 조회
        UserEntity existingUser = userMyPageRepository.findByUserId(userDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("해당 사용자를 찾을 수 없습니다. ID: " + userDTO.getUserId()));

        boolean isPwdChanged = false; // 비밀번호 변경 여부를 추적하는 변수

        //비밀번호가 변경되었으면 암호화처리
        if (userDTO.getUserPwd() != null && !userDTO.getUserPwd().isEmpty()) {
            String encodedPassword = passwordEncoder.encode(userDTO.getUserPwd());
            existingUser.setUserPwd(encodedPassword);
            isPwdChanged = true; // 비밀번호가 변경되었음을 표시
        }

        // 변경할 필드 업데이트
        existingUser.setUserName(userDTO.getUserName());
        existingUser.setUserNickname(userDTO.getUserNickname());
        existingUser.setUserPhone(userDTO.getUserPhone());
        existingUser.setUserAddress(userDTO.getUserAddress());
        existingUser.setBirth(userDTO.getBirth());

        // 파일 처리 로직 (파일이 제공되었을 경우)
        if (file != null && !file.isEmpty()) {
            //  파일경로 변수
            String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\pro_files";

//        파일명 변수
            UUID uuid = UUID.randomUUID();
            String fileName = uuid + "_" + file.getOriginalFilename();

//        파일 객체 생성
            File saveFile = new File(projectPath, fileName);

//        생성한 파일 객체에 첨부파일 넣기
            file.transferTo(saveFile);

            // 파일 처리 로직...
            existingUser.setFilename(fileName);
            existingUser.setFilePath("/pro_files/" + fileName);
            existingUser.setProfileImagePath("/pro_files/" + fileName);
        }
        // 엔티티 저장 (이 경우 JPA는 변경 감지 기능을 사용하여 업데이트 쿼리를 실행)
        userMyPageRepository.save(existingUser);

        return isPwdChanged;
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
