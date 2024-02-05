package com.project.dasuri.admin.controller;

import com.project.dasuri.admin.dto.FaqDTO;
import com.project.dasuri.admin.dto.NoticeDTO;
import com.project.dasuri.admin.service.Admin_MoonService;
import com.project.dasuri.admin.service.FaqService;
import com.project.dasuri.admin.service.NoticeService;
import com.project.dasuri.community.dto.CommunityDto;
import jakarta.validation.Valid;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class NoticeController {

//    생성자 주입
    private final NoticeService noticeService;
    private final FaqService faqService;
    private final Admin_MoonService adminMoonService;

    @RequestMapping("/admin_notice")
    public String admin_notice(@PageableDefault(page = 1) Pageable pageable, Model model) {
        // 일반공지 페이징 (고유번호 내림차순)
        Page<NoticeDTO> normalDTOs = noticeService.admin_paging(pageable); // 글번호, 제목, 수정일자

        // faq 페이징 (고유번호 내림차순)
        Page<FaqDTO> faqDTOS = faqService.admin_paging(pageable);

        // 현재 페이지에서 앞 뒤 갯수
        int blockLimit = 5;

        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = ((startPage + blockLimit - 1) < normalDTOs.getTotalPages()) ? startPage + blockLimit - 1 : normalDTOs.getTotalPages();

        int startPage_faq = (((int) (Math.ceil((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage_faq = ((startPage_faq + blockLimit - 1) < faqDTOS.getTotalPages()) ? startPage_faq + blockLimit - 1 : faqDTOS.getTotalPages();

        model.addAttribute("normals", normalDTOs); // 일반공지(페이징)
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        model.addAttribute("faqList", faqDTOS); // faq(페이징)
        model.addAttribute("startPage_faq", startPage_faq);
        model.addAttribute("endPage_faq", endPage_faq);

        List<NoticeDTO> importantDTOs = noticeService.findByImportantNotNull();
        model.addAttribute("importants", importantDTOs); // 중요공지

        model.addAttribute("moons", adminMoonService.admin_paging(PageRequest.of(1, 7))); // 푸터용
        return "/adminad/admin_notice";
    }


    //    관리자페이지 > 공지관리 > 공지올리기 (작성 폼)
    @RequestMapping("/admin_notice_write")
    public String admin_notice_write(Model model) {
        model.addAttribute("moons", adminMoonService.admin_paging(PageRequest.of(1, 7))); // 푸터용
        return "adminad/admin_notice_write";
    }


    //    관리자페이지 > 공지관리 > 공지올리기 (작성 완료)
    @PostMapping("/admin_notice_write_ok")
    public String admin_notice_write_ok(@Valid NoticeDTO noticeDTO, @RequestParam("file") MultipartFile file, Model model) throws IOException {
        noticeDTO.setNoticeContent(noticeDTO.getNoticeContent().replace("\r\n","<br>"));
        noticeService.save(noticeDTO, file);
        model.addAttribute("moons", adminMoonService.admin_paging(PageRequest.of(1, 7))); // 푸터용
        return "redirect:/admin_notice";
    }

    //    관리자페이지 > 공지관리 > 공지보기 (내용보기 및 수정삭제)
    @GetMapping("/admin_notice_view/{id}")
    public String admin_notice_view(@PathVariable Long id,Model model) {
        NoticeDTO noticeDTO = noticeService.findByNoticeId(id);
        if (noticeDTO.getImportant() == null) {
            noticeDTO.setNotice_type("일반");
        }else{
            noticeDTO.setNotice_type("중요");
        }
        model.addAttribute("notice",noticeDTO);
        model.addAttribute("moons", adminMoonService.admin_paging(PageRequest.of(1, 7))); // 푸터용
        return "adminad/admin_notice_look";
    }

    //    관리자페이지 > 공지관리 > 공지보기 > 수정
//    @GetMapping("/admin_notice_update")
    @GetMapping("/admin_notice_update/{notice_id}")
    public String admin_notice_update(@PathVariable Long notice_id, Model model) {
        model.addAttribute("notice",noticeService.findByNoticeId(notice_id));
        model.addAttribute("moons", adminMoonService.admin_paging(PageRequest.of(1, 7))); // 푸터용
        return "adminad/admin_notice_update";
    }

    //    관리자페이지 > 공지관리 > 공지보기 > 수정 (완료)
    @PostMapping("/admin_notice_update_ok")
    public String admin_notice_update_ok(@Valid NoticeDTO noticeDTO, @RequestParam("file") MultipartFile file, Model model) throws IOException {
        noticeDTO.setNoticeContent(noticeDTO.getNoticeContent().replace("\r\n", "<br>"));
        noticeService.update(noticeDTO, file);
        model.addAttribute("notice", noticeDTO);
        model.addAttribute("moons", adminMoonService.admin_paging(PageRequest.of(1, 7))); // 푸터용
        return "adminad/admin_notice_look";
    }


    //    관리자페이지 > 공지관리 > 공지보기 > 삭제
    @Transactional
    @GetMapping("/admin_notice_delete/{notice_id}")
    public String admin_notice_delete(@PathVariable Long notice_id, Model model) {
        noticeService.deleteByNoticeId(notice_id);
        model.addAttribute("moons", adminMoonService.admin_paging(PageRequest.of(1, 7))); // 푸터용
        return "redirect:/admin_notice";
    }

    //    관리자페이지 > 공지관리 > 공지검색
    @RequestMapping("/admin_notice_search")
    public String notice_search2(@RequestParam String notice_keyword, @PageableDefault(page = 1) Pageable pageable, Model model) {

        //        일반공지 페이징 (고유번호 내림차순)
        Page<NoticeDTO> normalDTOs = noticeService.admin_searchNo(pageable,notice_keyword); //글번호, 제목, 수정일자

        int blockLimit  = 5;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) -1) * blockLimit + 1;
        int endPage = ((startPage + blockLimit - 1) < normalDTOs.getTotalPages()) ? startPage + blockLimit - 1 : normalDTOs.getTotalPages();

        model.addAttribute("normals",normalDTOs); //일반공지리스트(페이징)
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);

        List<NoticeDTO> importantDTOs = noticeService.searchIm(notice_keyword);
        Page<FaqDTO> faqDTOS = faqService.admin_paging(pageable);

        model.addAttribute("importants",importantDTOs); //중요공지 검색결과
        model.addAttribute("keyword",notice_keyword); //검색한 키워드
        model.addAttribute("faqList",faqDTOS); //같은 화면에 담을 faq리스트

        model.addAttribute("moons", adminMoonService.admin_paging(PageRequest.of(1, 7))); // 푸터용

        return "/adminad/admin_notice_search";
    }

}
