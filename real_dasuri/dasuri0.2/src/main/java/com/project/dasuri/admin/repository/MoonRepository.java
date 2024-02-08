package com.project.dasuri.admin.repository;

import com.project.dasuri.admin.entity.MoonEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MoonRepository extends JpaRepository<MoonEntity, Long> {
    Optional<MoonEntity> findByMoonPkId(Long id);

    List<MoonEntity> findByMoonUserId(String id);

    Page<MoonEntity> findByMoonUserIdContainingOrMoonQuestionContainingOrMoonAnswerContainingOrMoonTitleContaining(String keyword1, String keyword2, String keyword3, String keyword4, Pageable pageable);
    Page<MoonEntity> findByMoonStatusContaining(String status, Pageable pageable);

    Long countByMoonQuestionDateBetween(LocalDateTime localDateTime1, LocalDateTime localDateTime2);

    Page<MoonEntity> findByMoonUserId(String userId, Pageable pageable);
}
