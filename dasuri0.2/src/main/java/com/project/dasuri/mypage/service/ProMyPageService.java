package com.project.dasuri.mypage.service;

import com.project.dasuri.admin.dto.NoticeDTO;
import com.project.dasuri.admin.entity.NoticeEntity;
import com.project.dasuri.member.dto.ProDTO;
import com.project.dasuri.member.entity.ProEntity;
import com.project.dasuri.member.repository.ProRepository;
import com.project.dasuri.mypage.Repository.ProMyPageRepository;
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
public class ProMyPageService {
    private final ProMyPageRepository proMyPageRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public boolean update(ProDTO proDTO, MultipartFile file) throws IOException {
//    public void update(ProDTO proDTO, MultipartFile file) throws IOException {
        // 기존 정보 조회
        ProEntity existingPro = proMyPageRepository.findByProId(proDTO.getProId())
                .orElseThrow(() -> new EntityNotFoundException("해당 사용자를 찾을 수 없습니다. ID: " + proDTO.getProId()));

        boolean isPwdChanged = false; // 비밀번호 변경 여부를 추적하는 변수

        //비밀번호가 변경되었으면 암호화처리
        if (proDTO.getProPwd() != null && !proDTO.getProPwd().isEmpty()) {
            String encodedPassword = passwordEncoder.encode(proDTO.getProPwd());
            existingPro.setProPwd(encodedPassword);
            isPwdChanged = true; // 비밀번호가 변경되었음을 표시
        }

        // 변경할 필드 업데이트
        existingPro.setProName(proDTO.getProName());
        existingPro.setProNickname(proDTO.getProNickname());
        existingPro.setProPhone(proDTO.getProPhone());
        existingPro.setProLegions(proDTO.getProLegions());
        existingPro.setBirth(proDTO.getBirth());

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
            existingPro.setFilename(fileName);
            existingPro.setFilePath("/pro_files/" + fileName);
            existingPro.setProfileImagePath("/pro_files/" + fileName);
        }
        // 엔티티 저장 (이 경우 JPA는 변경 감지 기능을 사용하여 업데이트 쿼리를 실행)
        proMyPageRepository.save(existingPro);

        return isPwdChanged;
    }

//    @Transactional
//    public void update (ProDTO proDTO, MultipartFile file) throws IOException {
//        System.out.println("이미지 수정 메소드");
//        ProEntity proEntity = ProEntity.toProEntity(proDTO);
//
//        if (file != null && !file.isEmpty()) {
////        파일경로 변수
//            String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\pro_files";
//
////        파일명 변수
//            UUID uuid = UUID.randomUUID();
//            String fileName = uuid + "_" + file.getOriginalFilename();
//
////        파일 객체 생성
//            File saveFile = new File(projectPath, fileName);
//
////        생성한 파일 객체에 첨부파일 넣기
//            file.transferTo(saveFile);
//
////        엔티티에 파일명 + 파일 경로 저장
//            proEntity.setFilename(fileName);
//            proEntity.setFilePath("/pro_files/" + fileName);
//            proEntity.setProfileImagePath("/pro_files/" + fileName);
//        }
//        proRepository.save(proEntity);
//    }
}
