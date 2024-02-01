package com.project.dasuri.admin.entity;

import com.project.dasuri.admin.dto.FaqDTO;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "faq_table")
public class FaqEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //고유값을 자동 생성 (auto_increment)
    @Column(name = "faq_id")
    private Long faqId; //faq 고유번호

    @Column
    private String faqQuestion; //faq 질문

    @Column
    private String faqAnswer; //faq 답변

    @Column(name = "faq_tag")
    private String faqTag; //faq 해시태그

    @CreationTimestamp
    @Column(updatable = false, name = "faq_firstDate")
    private LocalDateTime faq_firstDate = LocalDateTime.now(); //공지 첫 작성시간

    @UpdateTimestamp
    @Column(name = "faq_updateDate", nullable = true)
    private LocalDateTime faq_updateDate = LocalDateTime.now(); //공지 수정시간

//    DTO객체를 DB를 오가는 엔티티 객체로 변환
    public static FaqEntity toFaqEntity(FaqDTO faqDTO){
        FaqEntity faqEntity = new FaqEntity();
        faqEntity.setFaqQuestion(faqDTO.getFaqQuestion());
        faqEntity.setFaqAnswer(faqDTO.getFaqAnswer());
        faqEntity.setFaqTag(faqDTO.getFaqTag());
        return faqEntity;
    }

//    DTO객체를 DB를 오가는 엔티티 객체로 변환 (수정)
    public static FaqEntity toUpdateFaqEntity(FaqDTO faqDTO){
        FaqEntity faqEntity = new FaqEntity();
        faqEntity.setFaqId(faqDTO.getFaqId());
        faqEntity.setFaqQuestion(faqDTO.getFaqQuestion());
        faqEntity.setFaqAnswer(faqDTO.getFaqAnswer());
        faqEntity.setFaqTag(faqDTO.getFaqTag());
        return faqEntity;
    }
}










