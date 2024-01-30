package com.project.dasuri.community.service;

import com.project.dasuri.community.dto.CommunityDto;
import com.project.dasuri.community.entity.CommunityEntity;
import com.project.dasuri.community.repository.CommunityRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//DTO -> Entity (Entuty Class)
//Entity -> DTO (DTO Class)
@Service
@RequiredArgsConstructor

public class CommunityService {
    private  final CommunityRepository communityRepository;
    public void save(CommunityDto communityDto) {
        CommunityEntity communityEntity = CommunityEntity.toSaveEntity(communityDto);
        communityRepository.save(communityEntity);
    }

//    리스트로 들어가있는 값 찾기
    public List<CommunityDto> findAll() {
       List<CommunityEntity> communityEntityList = communityRepository.findAll();
       List<CommunityDto> communityDtoList = new ArrayList<>();
       for(CommunityEntity communityEntity: communityEntityList){
           communityDtoList.add(CommunityDto.toCommunityDto(communityEntity));
       }
       return communityDtoList;
    }


//    조회수증가
    @Transactional
    public void updateHits(Long id) {
        communityRepository.updateHits(id);
    }
    @Transactional
    public CommunityDto findById(Long id){
        Optional<CommunityEntity> optionalCommunityEntity = communityRepository.findById(id);
        if(optionalCommunityEntity.isPresent()){
            CommunityEntity communityEntity = optionalCommunityEntity.get();
            CommunityDto communityDto = CommunityDto.toCommunityDto(communityEntity);
            return communityDto;
        }else{
            return null;
        }
    }

//    업데이트
    public CommunityDto update(CommunityDto communityDto) {
        CommunityEntity communityEntity =CommunityEntity.toUpdateEntity(communityDto);
        communityRepository.save(communityEntity);

        return findById(communityDto.getId());
    }

    //삭제
    public void delete(Long id){
        communityRepository.deleteById(id);
    }

    public Page<CommunityDto> paging(Pageable pageable) {
        int page = pageable.getPageNumber()-1;
        int pageLimit = 10; //한페이지에 보여줄 글 갯수
//        한페이지당 3개씩 글을 보여주고 정렬 기준은 id 기준으로 내림차순 정렬
//        page 위치에 있는 값은 0부터 시작
        Page<CommunityEntity> communityEntities =
                communityRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC,"id")));
//        목록: id, wirter, title, hits, createTime
        Page<CommunityDto> communityDtos = communityEntities.map(community ->new CommunityDto(community.getId(),community.getCommuWriter(),community.getCommuTitle(),community.getCommuHits(),community.getCreatedTime()));
        return communityDtos;
    }
}
