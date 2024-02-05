package com.project.dasuri.admin.service;

import com.project.dasuri.admin.dto.FaqDTO;
import com.project.dasuri.admin.dto.NoticeDTO;
import com.project.dasuri.admin.entity.FaqEntity;
import com.project.dasuri.admin.entity.NoticeEntity;
import com.project.dasuri.admin.repository.FaqRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
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

    //    관리자리스트 faq 리스트 불러오기 (페이징)
    public Page<FaqDTO> admin_paging(Pageable pageable){
        int page = pageable.getPageNumber() -1; //page 값은 0부터 시작하므로 1 뺌 (디폴트 1 요청 시 -1)
        int pageLimit = 5; // 한 페이지당 글 5개

        Page<FaqEntity> faqEntities =
                faqRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "faqId")));
        Page<FaqDTO> faqDTOS = faqEntities.map(faq -> new FaqDTO(faq.getFaqId(),faq.getFaqQuestion(),faq.getFaqTag(),faq.getFaqAnswer()));

        return faqDTOS;
    }

//    faq 리스트 불러오기 (해시태그 필터링 - 페이징)
    public Page<FaqDTO> findByFaqTag(Pageable pageable, String faqTag){

        int page = pageable.getPageNumber() -1; //page 값은 0부터 시작하므로 1 뺌 (디폴트 1 요청 시 -1)
        int pageLimit = 5; // 한 페이지당 글 5개

        Page<FaqEntity> faqEntities =
                faqRepository.findByFaqTag(faqTag, PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "faqId")));
        Page<FaqDTO> faqDTOS = faqEntities.map(faq -> new FaqDTO(faq.getFaqId(),faq.getFaqQuestion(),faq.getFaqTag(),faq.getFaqAnswer()));

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

//    faq 검색 (페이징)
    public Page<FaqDTO> searchFaq(String keyword, Pageable pageable){
        int page = pageable.getPageNumber() -1; //page 값은 0부터 시작하므로 1 뺌 (디폴트 1 요청 시 -1)
        int pageLimit = 5; // 한 페이지당 글 5개

        Page<FaqEntity> faqEntities = faqRepository.findByFaqQuestionContainingOrFaqAnswerContainingOrFaqTagContaining(keyword,keyword,keyword,PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "faqId")));
        Page<FaqDTO> faqDTOS = faqEntities.map(faq -> new FaqDTO(faq.getFaqId(),faq.getFaqQuestion(),faq.getFaqTag(),faq.getFaqAnswer()));
        return faqDTOS;
    }
}
