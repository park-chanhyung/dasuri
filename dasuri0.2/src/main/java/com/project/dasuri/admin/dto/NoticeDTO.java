package com.project.dasuri.admin.dto;

import com.project.dasuri.admin.entity.NoticeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDTO {
    private Long notice_id; //공지 고유번호
    private String notice_title; //공지 제목
    private String notice_content; //공지 내용
    private LocalDateTime notice_firstDate; //공지 첫 작성시간
    private LocalDateTime notice_updateDate; //공지 수정시간

//    DB를 오가는 엔티티 객체를 DTO 객체로 변환
    public static NoticeDTO toNoticeDTO(NoticeEntity noticeEntity){
        NoticeDTO noticeDTO = new NoticeDTO();
        noticeDTO.setNotice_id(noticeEntity.getNoticeId());
        noticeDTO.setNotice_title(noticeEntity.getNotice_title());
        noticeDTO.setNotice_content(noticeEntity.getNotice_content());
        noticeDTO.setNotice_firstDate(noticeEntity.getNotice_firstDate());
        noticeDTO.setNotice_updateDate(noticeEntity.getNotice_updateDate());
        return noticeDTO;
    }
}
