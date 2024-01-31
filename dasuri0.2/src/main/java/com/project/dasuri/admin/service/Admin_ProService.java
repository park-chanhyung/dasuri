package com.project.dasuri.admin.service;

import com.project.dasuri.admin.repository.AdminProRepository;
import com.project.dasuri.member.dto.ProDTO;
import com.project.dasuri.member.entity.ProEntity;
import com.project.dasuri.member.repository.ProRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Admin_ProService {
    private final AdminProRepository proRepository;

    //    기사리스트
    public List<ProDTO> findAll(){
        List<ProEntity> proEntities = proRepository.findAll();
        List<ProDTO> proDTOS = new ArrayList<>();
        for (ProEntity proEntity : proEntities){
            proDTOS.add(ProDTO.toProDTO(proEntity));
        }
        return proDTOS;
    }

    //    기사정보 조회
    public ProDTO findByProId(String proId){
        Optional<ProEntity> proEntity = proRepository.findByProId(proId);
        if (proEntity.isPresent()){
            return ProDTO.toProDTO(proEntity.get());
        }else {
            return null;
        }
    }
}
