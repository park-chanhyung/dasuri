package com.project.dasuri.community.repository;

import com.project.dasuri.community.entity.CommentEntity;
import com.project.dasuri.community.entity.CommunityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository  extends JpaRepository<CommentEntity, Long> {
    //        select * from comment_table where board_id=? order by desc;
    List<CommentEntity> findAllByCommunityEntityOrderByIdDesc(CommunityEntity communityEntity);
}
