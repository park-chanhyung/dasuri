package com.project.dasuri.admin.service;

import com.project.dasuri.admin.dto.MoonDTO;
import com.project.dasuri.admin.entity.MoonEntity;
import com.project.dasuri.admin.repository.MoonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MoonService {
    private final MoonRepository moonRepository;

//    문의 신규 등록 (첨부파일 포함)
    public void questionSave(MoonDTO moonDTO, MultipartFile file) throws IOException {

        MoonEntity moonEntity = MoonEntity.toQuestionMoonEntity(moonDTO);

        if (file != null && !file.isEmpty()) {
//        파일경로 변수
            String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\moon_files";

//        파일명 변수
            UUID uuid = UUID.randomUUID();
            String fileName = uuid + "_" + moonDTO.getFile().getOriginalFilename();

//        파일 객체 생성
            File saveFile = new File(projectPath, fileName);

//        생성한 파일 객체에 파일경로, 파일명 넣기
            file.transferTo(saveFile);

//        엔티티에 파일명 + 파일 경로 저장
            moonEntity.setFilename(fileName);
            moonEntity.setFilePath("/moon_files/" + fileName);
        }
        moonRepository.save(moonEntity);
    }


//    문의글에 답변 등록
    public void answerSave(MoonDTO moonDTO) throws IOException {
        MoonEntity moonEntity = MoonEntity.toAnswerMoonEntity(moonDTO);
        moonRepository.save(moonEntity);
    }

}
