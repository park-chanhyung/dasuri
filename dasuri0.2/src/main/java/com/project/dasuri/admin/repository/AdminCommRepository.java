package com.project.dasuri.admin.repository;

import com.project.dasuri.community.entity.CommunityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AdminCommRepository extends JpaRepository<CommunityEntity, Long> {

    List<CommunityEntity> findByUserIdContainingOrCommuTitleContainingOrCommuContentsContaining(String k1,String k2,String k3);

    Long countByCreatedTimeBetween(LocalDateTime localDateTime1,LocalDateTime localDateTime2);
}
