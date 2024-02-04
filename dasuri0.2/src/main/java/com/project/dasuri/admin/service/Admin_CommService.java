package com.project.dasuri.admin.service;

import com.project.dasuri.admin.repository.AdminCommRepository;
import com.project.dasuri.community.dto.CommunityDto;
import com.project.dasuri.community.entity.CommunityEntity;
import com.project.dasuri.community.repository.CommunityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class Admin_CommService {
    private final CommunityRepository communityRepository;
    private final AdminCommRepository adminCommRepository;

//    관리자페이지 > 커뮤니티 게시물 목록 전체조회
    public List<CommunityDto> findAll(){
        List<CommunityEntity> communityEntities = communityRepository.findAll();
        List<CommunityDto> communityDtos = new ArrayList<>();
        for (CommunityEntity communityEntity : communityEntities) {
            communityDtos.add(CommunityDto.toCommunityDto(communityEntity));
        }
        return communityDtos;
    }

//    관리자페이지 > 커뮤니티 > 검색
    public List<CommunityDto> searchComm(String keyword){
        List<CommunityEntity> communityEntities = adminCommRepository.findByUserIdContainingOrCommuTitleContainingOrCommuContentsContaining(keyword,keyword,keyword);
        List<CommunityDto> communityDtos = new ArrayList<>();
        for (CommunityEntity communityEntity : communityEntities) {
            communityDtos.add(CommunityDto.toCommunityDto(communityEntity));
        }
        return communityDtos;
    }

}
