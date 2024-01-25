package com.project.dasuri.admin.repository;

import com.project.dasuri.admin.entity.NoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NoticeRepository extends JpaRepository<NoticeEntity, Long> {
//    고유번호로 공지글 조회 (select * from notice_table where notice_id=?)
    Optional<NoticeEntity> findByNoticeId(Long noticeId);

}
