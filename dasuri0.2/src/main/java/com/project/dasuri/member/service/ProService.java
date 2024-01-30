package com.project.dasuri.member.service;

import com.project.dasuri.member.dto.ProDTO;
import com.project.dasuri.member.entity.ProEntity;
import com.project.dasuri.member.repository.ProRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProService {
    //repository 호출
    private final ProRepository proRepository;

    public void pro_signup(ProDTO proDTO){
        ProEntity proEntity = ProEntity.toProEntity(proDTO);
        proRepository.save(proEntity);

    }
}
