package com.project.dasuri.admin.entity;

import com.project.dasuri.admin.dto.NoticeDTO;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "notice_table")
public class NoticeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //고유값을 자동 생성 (auto_increment)
    @Column(name = "notice_id")
    private Long noticeId; //공지 고유번호

    @Column
    private String notice_title; //공지 제목

    @Column
    private String notice_content; //공지 내용

    @CreationTimestamp
    @Column(updatable = false, name = "notice_firstDate")
    private LocalDateTime notice_firstDate = LocalDateTime.now(); //공지 첫 작성시간

    @UpdateTimestamp
    @Column(name = "notice_updateDate", nullable = true)
    private LocalDateTime notice_updateDate = LocalDateTime.now(); //공지 수정시간

//    DTO객체를 DB를 오가는 엔티티 객체로 변환
    public static NoticeEntity toNoticeEntity(NoticeDTO noticeDTO){
        NoticeEntity noticeEntity = new NoticeEntity();
        noticeEntity.setNotice_title(noticeDTO.getNotice_title());
        noticeEntity.setNotice_content(noticeDTO.getNotice_content());
        return noticeEntity;
    }

//    DTO객체를 DB를 오가는 엔티티 객체로 변환 (수정)
    public static NoticeEntity toUpdateNoticeEntity(NoticeDTO noticeDTO){
        NoticeEntity noticeEntity = new NoticeEntity();
        noticeEntity.setNoticeId(noticeDTO.getNotice_id()); //고유번호
        noticeEntity.setNotice_title(noticeDTO.getNotice_title());
        noticeEntity.setNotice_content(noticeDTO.getNotice_content());
//        noticeEntity.setNotice_updateDate(LocalDateTime.now());
        return noticeEntity;
    }
}










