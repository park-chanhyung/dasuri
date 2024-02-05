package com.project.dasuri.community.repository;

import com.project.dasuri.community.entity.CommunityEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommunityRepository extends JpaRepository<CommunityEntity, Long> {
// upadte community set commuHits =commuHits+1 where id=?
    @Modifying
    @Query(value = "update CommunityEntity c set c.commuHits=c.commuHits+1 where c.id=:id")
    void updateHits(@Param("id") Long id);

    Page<CommunityEntity> findBycommuTitleContainingOrUserIdContaining(String titleKeyword, String userId, Pageable pageable);
}
