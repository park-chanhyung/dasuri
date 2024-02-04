package com.project.dasuri.admin.dto;

import com.project.dasuri.admin.entity.MoonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoonDTO {
    private Long moonPkId; //문의글 고유번호

    private String moonUserId; //문의한 회원 아이디

    private String moonRole; //문의한 회원 유형 (1고객 or 2기사)
    private String moonType; //문의글 유형 (1문의 or 2신고)

    private String moonStatus; //문의글 상태 (1대기 or 2완료)

    private String moonTitle; //문의글 제목
    private String moonQuestion; //문의글 내용
    private String moonAnswer; //문의글 답변

    private MultipartFile file; //첨부파일
    private String filename; //첨부파일명(uuid)
    private String filePath;//첨부파일경로

    private LocalDateTime moonQuestionDate; //문의한 시간
    private LocalDateTime moonAnswerDate; //답변한 시간


//    조회한 엔티티 객체를 DTO로 변환
    public static MoonDTO toMoonDTO(MoonEntity moonEntity){
        MoonDTO moonDTO = new MoonDTO();

        moonDTO.setMoonPkId(moonEntity.getMoonPkId()); //문의글 고유번호
        moonDTO.setMoonUserId(moonEntity.getMoonUserId()); //문의한 회원
        moonDTO.setMoonRole(moonEntity.getMoonRole()); //회원유형 1고객 2기사
        moonDTO.setMoonType(moonEntity.getMoonType()); //문의유형 1문의 2신고
        moonDTO.setMoonStatus(moonEntity.getMoonStatus()); //문의상태 1대기 2완료

        moonDTO.setMoonTitle(moonEntity.getMoonTitle()); //문의제목
        moonDTO.setMoonQuestion(moonEntity.getMoonQuestion()); //문의내용
        moonDTO.setMoonAnswer(moonEntity.getMoonAnswer()); //답변내용

        moonDTO.setFilename(moonEntity.getFilename()); //파일명
        moonDTO.setFilePath(moonEntity.getFilePath()); //파일경로
        
        moonDTO.setMoonQuestionDate(moonEntity.getMoonQuestionDate()); //문의 시간
        moonDTO.setMoonAnswerDate(moonEntity.getMoonAnswerDate()); //답변시간

        return moonDTO;
    }


}
