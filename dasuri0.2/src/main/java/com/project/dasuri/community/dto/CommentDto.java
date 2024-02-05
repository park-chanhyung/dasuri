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
    private String commentWriter;
    private String commentContents;
    private Long boardId; //게시글 번호
    private LocalDateTime commentCreatedTime;

    public static CommentDto toCommentDto(CommentEntity commentEntity, Long boardId) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(commentEntity.getId());
        commentDto.setCommentWriter(commentEntity.getCommentWriter());
        commentDto.setCommentContents(commentEntity.getCommentContents());
        commentDto.setCommentCreatedTime(commentEntity.getCreatedTime());
//        commentDto.setBoardId(commentEntity.getCommunityEntity().getId());
//        위의 구조를 쓰려면 Servie 메서드에 @Transactional
        commentDto.setBoardId(boardId);
        return commentDto;
    }
}
