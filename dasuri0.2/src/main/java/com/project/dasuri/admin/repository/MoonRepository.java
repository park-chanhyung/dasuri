package com.project.dasuri.admin.repository;

import com.project.dasuri.admin.entity.MoonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MoonRepository extends JpaRepository<MoonEntity, Long> {
    Optional<MoonEntity> findByMoonPkId(Long id);

    List<MoonEntity> findByMoonUserId(String id);

    List<MoonEntity> findByMoonUserIdContainingOrMoonQuestionContainingOrMoonAnswerContainingOrMoonTitleContaining(String keyword1,String keyword2,String keyword3,String keyword4);
}
