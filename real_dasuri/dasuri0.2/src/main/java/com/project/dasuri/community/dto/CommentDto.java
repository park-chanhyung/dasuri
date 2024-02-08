package com.project.dasuri.community.dto;

import com.project.dasuri.community.entity.CommentEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CommentDto {
    private Long id; //댓글 id값
    private String commentContents;
    private Long boardId; //게시글 번호
    private LocalDateTime commentCreatedTime;
    private String userId;
    private String userNickname;

    public static CommentDto toCommentDto(CommentEntity commentEntity, Long boardId) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(commentEntity.getId());
        commentDto.setCommentContents(commentEntity.getCommentContents());
        commentDto.setCommentCreatedTime(commentEntity.getCreatedTime());
        commentDto.setUserId(commentEntity.getUserId());
        commentDto.setUserNickname(commentEntity.getUserNickname());
//        commentDto.setBoardId(commentEntity.getCommunityEntity().getId());
//        위의 구조를 쓰려면 Servie 메서드에 @Transactional
        commentDto.setBoardId(boardId);
        return commentDto;
    }
}
