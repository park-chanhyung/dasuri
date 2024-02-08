package com.project.dasuri.community.service;

import com.project.dasuri.community.dto.CommentDto;
import com.project.dasuri.community.entity.CommentEntity;
import com.project.dasuri.community.entity.CommunityEntity;
import com.project.dasuri.community.repository.CommentRepository;
import com.project.dasuri.community.repository.CommunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private  final CommentRepository commentRepository;
    private  final CommunityRepository communityRepository;

    public Long save(CommentDto commentDto) {
//        부모엔티티 조회
            Optional<CommunityEntity> optionalCommunityEntity = communityRepository.findById(commentDto.getBoardId());
            if (optionalCommunityEntity.isPresent()){
                CommunityEntity communityEntity = optionalCommunityEntity.get();
                CommentEntity commentEntity = CommentEntity.toSaveEntity(commentDto, communityEntity);
                return commentRepository.save(commentEntity).getId();

            } else{
                return null;
            }
    }

    public List<CommentDto> findAll(Long boardId) {
//        select * from comment_table where board_id=? order by desc;
        CommunityEntity communityEntity = communityRepository.findById(boardId).get();
        List<CommentEntity> commentEntityList = commentRepository.findAllByCommunityEntityOrderByIdDesc(communityEntity);
//        EntityList=> DTOList
        List<CommentDto> commentDtoList = new ArrayList<>();
        for (CommentEntity commentEntity: commentEntityList){
            CommentDto commentDto = CommentDto.toCommentDto(commentEntity, boardId);
            commentDtoList.add(commentDto);
        }
        return commentDtoList;
    }
}









