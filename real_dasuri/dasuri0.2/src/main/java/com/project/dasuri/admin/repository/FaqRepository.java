package com.project.dasuri.admin.repository;

import com.project.dasuri.admin.entity.FaqEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FaqRepository extends JpaRepository<FaqEntity, Long> {

//    faqId 값으로 검색
    Optional<FaqEntity> findByFaqId(Long noticeId);
//    faqId 값으로 삭제
    Optional<FaqEntity> deleteByFaqId(Long noticeId);

//    faq : 해시태그로 검색하기
    Page<FaqEntity> findByFaqTag(String faqTag, Pageable pageable);

//    faq 검색 : 질문 or 답변 or 해시태그
    Page<FaqEntity> findByFaqQuestionContainingOrFaqAnswerContainingOrFaqTagContaining(String keyword1,String keyword2,String keyword3, Pageable pageable);

}
