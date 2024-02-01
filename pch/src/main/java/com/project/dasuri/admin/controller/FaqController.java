package com.project.dasuri.admin.controller;

import com.project.dasuri.admin.dto.FaqDTO;
import com.project.dasuri.admin.dto.NoticeDTO;
import com.project.dasuri.admin.entity.FaqEntity;
import com.project.dasuri.admin.service.FaqService;
import com.project.dasuri.admin.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class FaqController {

//    생성자 주입
    private final FaqService faqService;
    private final NoticeService noticeService;

    //    관리자페이지 > 공지관리 > faq올리기 (작성 폼)
    @RequestMapping("/admin_faq_write")
    public String admin_faq_write() {
        return "adminad/admin_faq_write";
    }

    //    관리자페이지 > 공지관리 > faq올리기 (작성 완료)
    @RequestMapping("/admin_faq_write_ok")
    public String admin_faq_write_ok(@ModelAttribute FaqDTO faqDTO) {
        faqService.save(faqDTO);
        return "redirect:/admin_notice";
    }

    //    관리자페이지 > 공지관리 > faq보기 (내용보기 및 수정삭제)
    @PostMapping("/admin_faq_view")
    public String admin_faq_view(@RequestParam Long id,Model model) {
        FaqDTO faqDTO = faqService.findByFaqId(id);
        model.addAttribute("faq",faqDTO);
        return "adminad/admin_faq_look";
    }

    //    관리자페이지 > 공지관리 > faq보기 > 수정
    @PostMapping("/admin_faq_update")
    public String admin_faq_update(@ModelAttribute FaqDTO faqDTO, Model model) {
        log.info("##@#$@ 태그 : "+faqDTO.getFaqTag());
        model.addAttribute("faq",faqDTO);
        return "adminad/admin_faq_update";
    }

    //    관리자페이지 > 공지관리 > faq보기 > 수정 (완료)
    @PostMapping("/admin_faq_update_ok")
    public String admin_faq_update_ok(@ModelAttribute FaqDTO faqDTO, Model model) {
        faqService.update(faqDTO);
        model.addAttribute("faq",faqDTO);
        return "redirect:/admin_notice";
    }

    //    관리자페이지 > 공지관리 > faq보기 > 삭제
    @Transactional
    @PostMapping("/admin_faq_delete")
    public String admin_faq_delete(@ModelAttribute FaqDTO faqDTO, Model model) {
        faqService.deleteByFaqId(faqDTO.getFaqId());
        model.addAttribute("faq",faqDTO);
        return "redirect:/admin_notice";
    }

    //    관리자페이지 > 공지관리 > faq검색
    @RequestMapping("/admin_faq_search")
    public String notice_search2(@RequestParam String faq_keyword, Model model) {

        List<FaqDTO> faqDTOS = faqService.searchFaq(faq_keyword); //faq 검색결과

        List<NoticeDTO> importantDTOs = noticeService.findByImportantNotNull(); //(같은 화면)중요공지리스트
        List<NoticeDTO> normalDTOs = noticeService.findByImportantNull(); //(같은 화면)일반공지리스트

        model.addAttribute("faqList",faqDTOS); //faq 검색결과
        model.addAttribute("keyword",faq_keyword); //검색한 키워드

        model.addAttribute("importants",importantDTOs);//(같은 화면)중요공지리스트
        model.addAttribute("normals",normalDTOs);//(같은 화면)일반공지리스트

        return "/adminad/admin_faq_search";
    }






}
