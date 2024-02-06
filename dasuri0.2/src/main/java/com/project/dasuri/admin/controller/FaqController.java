package com.project.dasuri.admin.controller;

import com.project.dasuri.admin.dto.FaqDTO;
import com.project.dasuri.admin.dto.NoticeDTO;
import com.project.dasuri.admin.service.Admin_MoonService;
import com.project.dasuri.admin.service.Admin_ReportService;
import com.project.dasuri.admin.service.FaqService;
import com.project.dasuri.admin.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class FaqController {

//    생성자 주입
    private final FaqService faqService;
    private final NoticeService noticeService;
    private final Admin_MoonService adminMoonService;
    private final Admin_ReportService adminReportService;

    //    관리자페이지 > 공지관리 > faq올리기 (작성 폼)
    @RequestMapping("/admin_faq_write")
    public String admin_faq_write(Model model) {
        model.addAttribute("moons", adminMoonService.admin_paging(PageRequest.of(1, 7))); // 푸터용
        model.addAttribute("report", adminReportService.todayReport()); // 푸터용2
        return "adminad/admin_faq_write";
    }

    //    관리자페이지 > 공지관리 > faq올리기 (작성 완료)
    @RequestMapping("/admin_faq_write_ok")
    public String admin_faq_write_ok(@ModelAttribute FaqDTO faqDTO,Model model) {
        faqService.save(faqDTO);
        model.addAttribute("moons", adminMoonService.admin_paging(PageRequest.of(1, 7))); // 푸터용
        model.addAttribute("report", adminReportService.todayReport()); // 푸터용2
        return "redirect:/admin_notice";
    }

    //    관리자페이지 > 공지관리 > faq보기 (내용보기 및 수정삭제)
    @GetMapping("/admin_faq_view/{id}")
    public String admin_faq_view(@PathVariable Long id,Model model) {
        FaqDTO faqDTO = faqService.findByFaqId(id);
        model.addAttribute("faq",faqDTO);
        model.addAttribute("moons", adminMoonService.admin_paging(PageRequest.of(1, 7))); // 푸터용
        model.addAttribute("report", adminReportService.todayReport()); // 푸터용2
        return "adminad/admin_faq_look";
    }

    //    관리자페이지 > 공지관리 > faq보기 > 수정
    @GetMapping("/admin_faq_update/{faqId}")
    public String admin_faq_update(@PathVariable Long faqId, Model model) {
        model.addAttribute("faq",faqService.findByFaqId(faqId));
        model.addAttribute("moons", adminMoonService.admin_paging(PageRequest.of(1, 7))); // 푸터용
        model.addAttribute("report", adminReportService.todayReport()); // 푸터용2
        return "adminad/admin_faq_update";
    }

    //    관리자페이지 > 공지관리 > faq보기 > 수정 (완료)
    @PostMapping("/admin_faq_update_ok/{id}")
    public String admin_faq_update_ok(@PathVariable Long id, @ModelAttribute FaqDTO faqDTO, Model model) {
        faqService.update(faqDTO);
        model.addAttribute("faq", faqDTO);
        model.addAttribute("moons", adminMoonService.admin_paging(PageRequest.of(1, 7))); // 푸터용
        model.addAttribute("report", adminReportService.todayReport()); // 푸터용2
        return "adminad/admin_faq_look";
    }

    //    관리자페이지 > 공지관리 > faq보기 > 삭제
    @Transactional
    @GetMapping("/admin_faq_delete/{faqId}")
    public String admin_faq_delete(@PathVariable Long faqId, Model model) {
        faqService.deleteByFaqId(faqId);
        model.addAttribute("moons", adminMoonService.admin_paging(PageRequest.of(1, 7))); // 푸터용
        model.addAttribute("report", adminReportService.todayReport()); // 푸터용2
        return "redirect:/admin_notice";
    }

    //    관리자페이지 > 공지관리 > faq검색
    @RequestMapping("/admin_faq_search")
    public String notice_search2(@RequestParam String faq_keyword, @PageableDefault(page = 1) Pageable pageable, Model model) {

        // 일반공지 페이징 (고유번호 내림차순)
        Page<NoticeDTO> normalDTOs = noticeService.admin_paging(pageable); // 글번호, 제목, 수정일자

        // faq 페이징 (고유번호 내림차순)
        Page<FaqDTO> faqDTOS = faqService.searchFaq(faq_keyword, pageable); //faq 검색결과

        // 현재 페이지에서 앞 뒤 갯수
        int blockLimit = 5;

        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = ((startPage + blockLimit - 1) < normalDTOs.getTotalPages()) ? startPage + blockLimit - 1 : normalDTOs.getTotalPages();

        int startPage_faq = (((int) (Math.ceil((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage_faq = ((startPage_faq + blockLimit - 1) < faqDTOS.getTotalPages()) ? startPage_faq + blockLimit - 1 : faqDTOS.getTotalPages();

        model.addAttribute("normals", normalDTOs); // 일반공지(페이징)
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        model.addAttribute("faqList", faqDTOS); // faq 검색결과 (페이징)
        model.addAttribute("startPage_faq", startPage_faq);
        model.addAttribute("endPage_faq", endPage_faq);
        model.addAttribute("keyword",faq_keyword); //검색한 키워드

        List<NoticeDTO> importantDTOs = noticeService.findByImportantNotNull();
        model.addAttribute("importants", importantDTOs); // 중요공지

        model.addAttribute("moons", adminMoonService.admin_paging(PageRequest.of(1, 7))); // 푸터용
        model.addAttribute("report", adminReportService.todayReport()); // 푸터용2
        return "/adminad/admin_faq_search";
    }
}