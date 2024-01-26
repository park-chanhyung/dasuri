package com.project.dasuri.admin.controller;

import com.project.dasuri.admin.dto.FaqDTO;
import com.project.dasuri.admin.dto.NoticeDTO;
import com.project.dasuri.admin.service.FaqService;
import com.project.dasuri.admin.service.NoticeService;
import lombok.RequiredArgsConstructor;
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
public class FaqController {

//    생성자 주입
    private final FaqService faqService;

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

}
