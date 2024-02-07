package com.project.dasuri.admin.repository;

import com.project.dasuri.community.entity.CommunityEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AdminCommRepository extends JpaRepository<CommunityEntity, Long> {

    Page<CommunityEntity> findByUserIdContainingOrCommuTitleContainingOrCommuContentsContainingOrUserNicknameContaining(String k1,String k2,String k3,String k4,Pageable pageable);

    Long countByCreatedTimeBetween(LocalDateTime localDateTime1,LocalDateTime localDateTime2);

    Page<CommunityEntity> findByAdminDeletedNotNull(Pageable pageable);

}
