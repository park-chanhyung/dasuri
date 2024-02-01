package com.project.dasuri.community.service;

import com.project.dasuri.community.dto.CommunityDto;
import com.project.dasuri.community.entity.CommunityEntity;
import com.project.dasuri.community.repository.CommunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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


    public List<CommunityDto> findAll() {
       List<CommunityEntity> communityEntityList = communityRepository.findAll();
       List<CommunityDto> communityDtoList = new ArrayList<>();
       for(CommunityEntity communityEntity: communityEntityList){
           communityDtoList.add(CommunityDto.toCommunityDto(communityEntity));
       }
       return communityDtoList;
    }
}
