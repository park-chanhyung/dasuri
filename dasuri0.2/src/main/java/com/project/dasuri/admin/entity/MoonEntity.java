package com.project.dasuri.admin.entity;

import com.project.dasuri.admin.dto.MoonDTO;
import com.project.dasuri.admin.dto.NoticeDTO;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "moon_table")
public class MoonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //고유값을 자동 생성 (auto_increment)
    @Column
    private Long moonPkId; //문의글 고유번호 (PK)

    @Column
    private String moonUserId; //문의한 회원 아이디

    @Column
    private String moonRole; //문의한 회원 유형 (1고객 or 2기사)

    @Column
    private String moonType; //문의글 유형 (1문의 or 2신고)

    @Column
    private String moonStatus = "1"; //문의글 상태 (1대기 or 2완료) --처음 문의 시 1, 관리자가 답변 시 2로 수정됨

    @Column
    private String moonTitle; //문의글 제목

    @Column
    private String moonQuestion; //문의글 내용

    @Column
    private String moonAnswer; //문의글 답변

    @Column(nullable = true)
    private String filename; //첨부파일명(uuid)

    @Column(nullable = true)
    private String filePath;//첨부파일경로

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime moonQuestionDate = LocalDateTime.now(); //문의 최초 등록 시 들어감

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime moonAnswerDate; //답변한 시간 (답변 때 들어감)

//    문의 신규 등록
    public static MoonEntity toQuestionMoonEntity(MoonDTO moonDTO) throws IOException {
        MoonEntity moonEntity = new MoonEntity();

        moonEntity.setMoonUserId(moonDTO.getMoonUserId()); //회원아이디
        moonEntity.setMoonRole(moonDTO.getMoonRole()); //회원유형 1고객 2기사
        moonEntity.setMoonType(moonDTO.getMoonType()); //문의유형 1문의 2신고
        moonEntity.setMoonStatus("1");
        moonEntity.setMoonTitle(moonDTO.getMoonTitle()); //제목
        moonEntity.setMoonQuestion(moonDTO.getMoonQuestion()); //문의내용

        // 첨부파일 컬럼은 서비스단에서 처리

        return moonEntity;
    }


//    문의 답변 등록 (작성 중..)
    public static MoonEntity toAnswerMoonEntity(MoonDTO moonDTO) throws IOException {
        MoonEntity moonEntity = new MoonEntity();

        moonEntity.setMoonPkId(moonDTO.getMoonPkId()); //고유번호 (새로 생성되지 않고 수정 형식으로 처리)

        moonEntity.setMoonUserId(moonDTO.getMoonUserId()); //회원아이디
        moonEntity.setMoonRole(moonDTO.getMoonRole()); //회원유형 1고객 2기사
        moonEntity.setMoonType(moonDTO.getMoonType()); //문의유형 1문의 2신고
        moonEntity.setMoonTitle(moonDTO.getMoonTitle()); //제목
        moonEntity.setMoonQuestion(moonDTO.getMoonQuestion()); //문의내용

//        답변 시 추가로 들어가거나 수정되는 컬럼
        moonEntity.setMoonStatus("2"); //답변 시 문의글 상태 2로 변경(1대기 2완료)
        moonEntity.setMoonAnswer(moonDTO.getMoonAnswer()); //답변내용
        moonEntity.setMoonAnswerDate(LocalDateTime.now()); //답변한 시간

        moonEntity.setFilename(moonDTO.getFilename()); //기존파일명
        moonEntity.setFilePath(moonDTO.getFilePath()); //기존파일경로

        return moonEntity;
    }

}
