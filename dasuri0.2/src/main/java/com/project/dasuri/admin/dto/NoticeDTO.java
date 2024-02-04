package com.project.dasuri.admin.dto;

import com.project.dasuri.admin.entity.NoticeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.format.DateTimeFormatter;

@Getter
@Setter
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

    private MultipartFile file; //첨부파일
    private String filename; //첨부파일명(uuid)
    private String filePath;//첨부파일경로

    public NoticeDTO(Long notice_id, String noticeTitle, String notice_updateDate) {
        this.notice_id = notice_id;
        this.noticeTitle = noticeTitle;
        this.notice_updateDate = notice_updateDate;
    }

    //    DB를 오가는 엔티티 객체를 DTO 객체로 변환
    public static NoticeDTO toNoticeDTO(NoticeEntity noticeEntity){
        NoticeDTO noticeDTO = new NoticeDTO();
        noticeDTO.setNotice_id(noticeEntity.getNoticeId());
        noticeDTO.setNoticeTitle(noticeEntity.getNoticeTitle());
        noticeDTO.setNoticeContent(noticeEntity.getNoticeContent());
        noticeDTO.setNotice_firstDate(noticeEntity.getNotice_firstDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        noticeDTO.setNotice_updateDate(noticeEntity.getNotice_updateDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        noticeDTO.setImportant(noticeEntity.getImportant());
        noticeDTO.setFilename(noticeEntity.getFilename());//파일명
        noticeDTO.setFilePath(noticeEntity.getFilePath());//파일경로
        return noticeDTO;
    }
}
