package com.project.dasuri.admin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.dasuri.admin.entity.NoticeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDTO {
    private Long notice_id; //공지 고유번호
    private String noticeTitle; //공지 제목
    private String noticeContent; //공지 내용
    private String notice_firstDate; //공지 첫 작성시간
    private String notice_updateDate; //공지 수정시간
    private String important; //중요 여부 (y or null)
    private String notice_type; //"중요" or "일반"
    private int notice_no; //공지사항 게시판에서의 일반공지 번호

    //    DB를 오가는 엔티티 객체를 DTO 객체로 변환
    public static NoticeDTO toNoticeDTO(NoticeEntity noticeEntity){
        NoticeDTO noticeDTO = new NoticeDTO();
        noticeDTO.setNotice_id(noticeEntity.getNoticeId());
        noticeDTO.setNoticeTitle(noticeEntity.getNoticeTitle());
        noticeDTO.setNoticeContent(noticeEntity.getNoticeContent());
        noticeDTO.setNotice_firstDate(noticeEntity.getNotice_firstDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        noticeDTO.setNotice_updateDate(noticeEntity.getNotice_updateDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        noticeDTO.setImportant(noticeEntity.getImportant());
        return noticeDTO;
    }
}
