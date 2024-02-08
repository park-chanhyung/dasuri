package com.project.dasuri.card.service;

import com.project.dasuri.admin.dto.MoonDTO;
import com.project.dasuri.admin.entity.MoonEntity;
import com.project.dasuri.card.repository.ProCardRepository;
import com.project.dasuri.member.dto.ProDTO;
import com.project.dasuri.member.entity.ProEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public Page<ProDTO> pro_paging(String proId, Pageable pageable){
        int page = pageable.getPageNumber() - 1; //page 값은 0부터 시작하므로 1 뺌 (디폴트 1 요청 시 -1)
        int pageLimit = 4; // 한 페이지당 글 7개

    //       로그인한 유저의 문의글 고유번호 기준으로 내림차순 (최신순)
        Page<ProEntity> proEntities =
                proCardRepository.findByProId(proId, PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "proId")));

    //        목록에 보여질 항목
        Page<ProDTO> proDTOS = proEntities.map
                (pro -> new ProDTO(pro.getProId(), pro.getProName(), pro.getProNickname(), pro.getProLegions(), pro.getSignupDate(), pro.getSuspensionExpiry()));
        return proDTOS;
    }
    public List<ProDTO> findAll(){
        List<ProEntity> proCardEntityList = proCardRepository.findAll();
        List<ProDTO> proCardDTOS = new ArrayList<>();
        for (ProEntity proCardEntity : proCardEntityList) {
            proCardDTOS.add(ProDTO.toProDTO(proCardEntity));
        }
        return proCardDTOS;
    }

//    public ProDTO findById(String proId) {
//        Optional<ProEntity> proEntityOptional = proCardRepository.findByProId(proId);
////            if (proEntityOptional.isPresent()) {
////                return ProDTO.toProDTO(proEntityOptional.get());
////            }else {
////                return null;
////            }
//        return proEntityOptional.map(ProDTO::toProDTO).orElse(null);
//    }
}
