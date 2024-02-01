package com.project.dasuri.admin.service;

import com.project.dasuri.admin.dto.FaqDTO;
import com.project.dasuri.admin.dto.NoticeDTO;
import com.project.dasuri.admin.entity.FaqEntity;
import com.project.dasuri.admin.entity.NoticeEntity;
import com.project.dasuri.admin.repository.FaqRepository;
import com.project.dasuri.admin.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FaqService {
    private final FaqRepository faqRepository;

//    faq 신규 등록
//    1. 폼에서 받은 dto객체를 엔티티 객체로 변환
//    2. repository의 save 메소드로 DB에 저장 (엔티티 객체를 넘겨야함)
    public void save(FaqDTO faqDTO){
        FaqEntity faqEntity = FaqEntity.toFaqEntity(faqDTO);
        faqRepository.save(faqEntity);
    }

//    faq 리스트 불러오기
    public List<FaqDTO> findAll(){
        List<FaqEntity> faqEntities = faqRepository.findAll(Sort.by(Sort.Direction.DESC,"faqId"));
        List<FaqDTO> faqDTOS = new ArrayList<>();
        int x = faqEntities.size();
        for (FaqEntity faqEntity : faqEntities) {
            FaqDTO faqDTO = FaqDTO.toFaqDto(faqEntity);
            faqDTO.setFaq_no(x);
            faqDTOS.add(faqDTO);
            x--;
        }
        return faqDTOS;
    }

//    faq 리스트 불러오기 (해시태그 필터링)
    public List<FaqDTO> findByFaqTag(String faqTag){
        List<FaqEntity> faqEntities = faqRepository.findByFaqTag(faqTag);
        List<FaqDTO> faqDTOS = new ArrayList<>();
        int x = faqEntities.size();
        for (FaqEntity faqEntity : faqEntities) {
            FaqDTO faqDTO = FaqDTO.toFaqDto(faqEntity);
            faqDTO.setFaq_no(x);
            faqDTOS.add(faqDTO);
            x--;
        }
        return faqDTOS;
    }

//    faq 불러오기
    public FaqDTO findByFaqId(Long id){
        Optional<FaqEntity> faqEntity = faqRepository.findByFaqId(id);
        if (faqEntity.isPresent()){
            return FaqDTO.toFaqDto(faqEntity.get());
        }else {
            return null;
        }
    }

//    faq 수정하기
    public void update (FaqDTO faqDTO){
        FaqEntity faqEntity = FaqEntity.toUpdateFaqEntity(faqDTO);
        faqRepository.save(faqEntity);
    }

//    faq 삭제하기
    public void deleteByFaqId(Long id) {
        faqRepository.deleteByFaqId(id);
    }

//    faq 검색하기
    public List<FaqDTO> searchFaq(String keyword){
        List<FaqEntity> faqEntities = faqRepository.findByFaqQuestionContainingOrFaqAnswerContainingOrFaqTagContaining(keyword,keyword,keyword);
//        OrderByNoticeIdDesc
        List<FaqDTO> faqDTOS = new ArrayList<>();
        int x = faqEntities.size();
        for (FaqEntity faqEntity : faqEntities) {
            FaqDTO faqDTO = FaqDTO.toFaqDto(faqEntity);
            faqDTO.setFaq_no(x); //FAQ 리스트에 번호매김
            faqDTOS.add(faqDTO);
            x--;
        }
        return faqDTOS;
    }







}
