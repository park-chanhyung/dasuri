package com.project.dasuri.community.entity;

import com.project.dasuri.community.dto.CommentDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="comment_table")
public class CommentEntity extends TimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String userId;

    @Column
    private String userNickname;

    @Column
    private String commentContents;

//    게시판:댓글 = 1:N
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commu_Id")
    private CommunityEntity communityEntity;



    public static CommentEntity toSaveEntity(CommentDto commentDto, CommunityEntity CommunityEntity) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setCommentContents(commentDto.getCommentContents());
        commentEntity.setCommunityEntity(CommunityEntity);
        commentEntity.setUserId(commentDto.getUserId());
        commentEntity.setUserNickname(commentDto.getUserNickname());

        return commentEntity;
    }
}
