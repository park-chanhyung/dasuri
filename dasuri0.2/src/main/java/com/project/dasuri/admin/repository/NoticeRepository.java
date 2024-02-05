package com.project.dasuri.admin.repository;

import com.project.dasuri.admin.entity.NoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoticeRepository extends JpaRepository<NoticeEntity, Long> {

//    고유번호로 공지글 조회 (select * from notice_table where notice_id=?)
    Optional<NoticeEntity> findByNoticeId(Long noticeId);

//    고유번호로 공지글 삭제
    Optional<NoticeEntity> deleteByNoticeId(Long noticeId);

//    중요공지 리스트
    List<NoticeEntity> findByImportantNotNullOrderByNoticeIdDesc();

//    일반공지 리스트
    List<NoticeEntity> findByImportantNullOrderByNoticeIdDesc();

//    일반공지 리스트 (페이징)
    Page<NoticeEntity> findByImportantNull(Pageable pageable);

//    중요공지 검색
List<NoticeEntity> findByImportantIsNotNullAndNoticeTitleContainingOrImportantIsNotNullAndNoticeContentContainingOrderByNoticeIdDesc(String titleKeyword, String contentKeyword);

//    일반공지 검색
List<NoticeEntity> findByImportantIsNullAndNoticeTitleContainingOrImportantIsNullAndNoticeContentContainingOrderByNoticeIdDesc(String titleKeyword, String contentKeyword);

// 일반공지 검색 (페이징)
    Page<NoticeEntity> findByImportantIsNullAndNoticeTitleContainingOrImportantIsNullAndNoticeContentContaining(String k1, String k2, Pageable pageable);
}
