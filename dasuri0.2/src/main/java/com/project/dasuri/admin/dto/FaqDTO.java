package com.project.dasuri.admin.dto;

import com.project.dasuri.admin.entity.FaqEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FaqDTO {
    private Long faqId; //faq 고유번호
    private String faqQuestion; //faq 질문
    private String faqAnswer; //faq 답변
    private String faq_firstDate; //faq 첫 작성시간
    private String faq_updateDate; //faq 수정시간
    private int faq_no; //나열할때 번호
    private String faqTag; //faq 해시태그

    //    DB를 오가는 엔티티 객체를 DTO 객체로 변환
    public static FaqDTO toFaqDto(FaqEntity faqEntity){
        FaqDTO faqDTO = new FaqDTO();
        faqDTO.setFaqId(faqEntity.getFaqId());
        faqDTO.setFaqTag(faqEntity.getFaqTag());
        faqDTO.setFaqQuestion(faqEntity.getFaqQuestion());
        faqDTO.setFaqAnswer(faqEntity.getFaqAnswer());
        faqDTO.setFaq_firstDate(faqEntity.getFaq_firstDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        faqDTO.setFaq_updateDate(faqEntity.getFaq_updateDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        return faqDTO;
    }
}
