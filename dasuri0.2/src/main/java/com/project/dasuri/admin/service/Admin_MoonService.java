package com.project.dasuri.admin.service;

import com.project.dasuri.admin.dto.MoonDTO;
import com.project.dasuri.admin.dto.NoticeDTO;
import com.project.dasuri.admin.entity.MoonEntity;
import com.project.dasuri.admin.entity.NoticeEntity;
import com.project.dasuri.admin.repository.MoonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class Admin_MoonService {
    private final MoonRepository moonRepository;

    //    문의글 리스트 불러오기
    public List<MoonDTO> findAll(){
        List<MoonEntity> moonEntities = moonRepository.findAll();
        List<MoonDTO> moonDTOS = new ArrayList<>();
        for (MoonEntity moonEntity : moonEntities) {
            moonDTOS.add(MoonDTO.toMoonDTO(moonEntity));
        }
        return moonDTOS;
    }
}
