package com.project.dasuri.admin.service;

import com.project.dasuri.admin.dto.MoonDTO;
import com.project.dasuri.admin.entity.MoonEntity;
import com.project.dasuri.admin.repository.AdminCommRepository;
import com.project.dasuri.community.dto.CommunityDto;
import com.project.dasuri.community.entity.CommunityEntity;
import com.project.dasuri.community.repository.CommunityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class Admin_CommService {
    private final CommunityRepository communityRepository;
    private final AdminCommRepository adminCommRepository;

    //    관리자페이지 -> 블라인드된 게시물 리스트 (페이징)
    public Page<CommunityDto> admin_paging(Pageable pageable){
        int page = pageable.getPageNumber() -1; //page 값은 0부터 시작하므로 1 뺌 (디폴트 1 요청 시 -1)
        int pageLimit = 7; // 한 페이지당 글 7개

//        '블라인드된' 게시물 고유번호 기준으로 내림차순 (최신순)
        Page<CommunityEntity> communityEntities =
                adminCommRepository.findByAdminDeletedNotNull(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));

        // 페이징에 가져갈 항목
        Page<CommunityDto> communityDtos = communityEntities.map
                (comm -> new CommunityDto(comm.getId(),comm.getCommuWriter(),comm.getCommuTitle(),comm.getCommuHits(),comm.getCreatedTime(),comm.getUserId(),comm.getRole(),comm.getUserNickname()));
        return communityDtos;
    }

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
