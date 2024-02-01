package com.project.dasuri.admin.entity;

import com.project.dasuri.admin.dto.NoticeDTO;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Data
@Table(name = "notice_table")
public class NoticeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //고유값을 자동 생성 (auto_increment)
    @Column(name = "notice_id")
    private Long noticeId; //공지 고유번호

    @Column
    private String noticeTitle; //공지 제목

    @Column
    private String noticeContent; //공지 내용

    //공지 첫 작성시간
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime notice_firstDate = LocalDateTime.now();

    //공지 수정시간
    @UpdateTimestamp
    @Column(name = "notice_updateDate")
    private LocalDateTime notice_updateDate = LocalDateTime.now();

    @Column(nullable = true)
    private String important; //중요 여부 (y or null)


//    DTO객체를 DB를 오가는 엔티티 객체로 변환
    public static NoticeEntity toNoticeEntity(NoticeDTO noticeDTO){
        NoticeEntity noticeEntity = new NoticeEntity();
        noticeEntity.setNoticeTitle(noticeDTO.getNoticeTitle());
        noticeEntity.setNoticeContent(noticeDTO.getNoticeContent());
        noticeEntity.setImportant(noticeDTO.getImportant()); //중요여부
        return noticeEntity;
    }

//    DTO객체를 DB를 오가는 엔티티 객체로 변환 (수정)
    public static NoticeEntity toUpdateNoticeEntity(NoticeDTO noticeDTO){
        NoticeEntity noticeEntity = new NoticeEntity();
        noticeEntity.setNoticeId(noticeDTO.getNotice_id()); //고유번호
        noticeEntity.setNoticeTitle(noticeDTO.getNoticeTitle());
        noticeEntity.setNoticeContent(noticeDTO.getNoticeContent());
        noticeEntity.setImportant(noticeDTO.getImportant()); //중요여부
        return noticeEntity;
    }
}










