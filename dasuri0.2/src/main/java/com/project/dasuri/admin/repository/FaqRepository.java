package com.project.dasuri.admin.repository;

import com.project.dasuri.admin.entity.FaqEntity;
import com.project.dasuri.admin.entity.NoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FaqRepository extends JpaRepository<FaqEntity, Long> {

    Optional<FaqEntity> findByFaqId(Long noticeId);
    Optional<FaqEntity> deleteByFaqId(Long noticeId);
}
