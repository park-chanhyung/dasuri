package com.project.dasuri.card.service;

import com.project.dasuri.card.repository.ProCardRepository;
import com.project.dasuri.member.dto.ProDTO;
import com.project.dasuri.member.entity.ProEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProCardService {
    //repository 호출
    private final ProCardRepository proCardRepository;

//    public void pro_signup(ProCardDTO proCardDTO){
//        ProCardEntity proCardEntity = ProCardEntity.toProEntity(proCardDTO);
//        proCardRepository.save(proCardEntity);
//
//    }
    public List<ProDTO> findAll(){
        List<ProEntity> proCardEntityList = proCardRepository.findAll();
        List<ProDTO> proCardDTOS = new ArrayList<>();
        for (ProEntity proCardEntity : proCardEntityList) {
            proCardDTOS.add(ProDTO.toProDTO(proCardEntity));
        }
        return proCardDTOS;
    }

    public ProDTO findById(String proId) {
        Optional<ProEntity> proEntityOptional = proCardRepository.findByProId(proId);
//            if (proEntityOptional.isPresent()) {
//                return ProDTO.toProDTO(proEntityOptional.get());
//            }else {
//                return null;
//            }
        return proEntityOptional.map(ProDTO::toProDTO).orElse(null);
    }
}
