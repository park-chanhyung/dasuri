package com.project.dasuri.admin.controller;

import com.project.dasuri.admin.dto.FaqDTO;
import com.project.dasuri.admin.dto.NoticeDTO;
import com.project.dasuri.admin.service.Admin_MoonService;
import com.project.dasuri.admin.service.FaqService;
import com.project.dasuri.admin.service.NoticeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    //  관리자페이지 > 공지관리 (공지&FAQ 리스트)
    @RequestMapping("/admin_notice")
    public String admin_notice(Model model) {
        List<FaqDTO> faqDTOS = faqService.findAll(); //faq리스트
        List<NoticeDTO> importantDTOs = noticeService.findByImportantNotNull(); //중요공지리스트
        List<NoticeDTO> normalDTOs = noticeService.findByImportantNull(); //일반공지리스트

        model.addAttribute("importants",importantDTOs);
        model.addAttribute("normals",normalDTOs);
        model.addAttribute("faqList",faqDTOS);

        model.addAttribute("moons",adminMoonService.findAll());

        return "/adminad/admin_notice";
    }


    //    관리자페이지 > 공지관리 > 공지올리기 (작성 폼)
    @RequestMapping("/admin_notice_write")
    public String admin_notice_write(Model model) {
        model.addAttribute("moons",adminMoonService.findAll());
        return "adminad/admin_notice_write";
    }


    //    관리자페이지 > 공지관리 > 공지올리기 (작성 완료)
    @PostMapping("/admin_notice_write_ok")
    public String admin_notice_write_ok(@Valid NoticeDTO noticeDTO, @RequestParam("file") MultipartFile file, Model model) throws IOException {
        noticeDTO.setNoticeContent(noticeDTO.getNoticeContent().replace("\r\n","<br>"));
        noticeService.save(noticeDTO, file);
        model.addAttribute("moons",adminMoonService.findAll());
        return "redirect:/admin_notice";
    }

    //    관리자페이지 > 공지관리 > 공지보기 (내용보기 및 수정삭제)
    @PostMapping("/admin_notice_view")
//    @GetMapping("/admin_notice_view/{id}")
    public String admin_notice_view(@RequestParam Long id,Model model) {
        NoticeDTO noticeDTO = noticeService.findByNoticeId(id);
        if (noticeDTO.getImportant() == null) {
            noticeDTO.setNotice_type("일반");
        }else{
            noticeDTO.setNotice_type("중요");
        }
        model.addAttribute("notice",noticeDTO);
        model.addAttribute("moons",adminMoonService.findAll());
        return "adminad/admin_notice_look";
    }

    //    관리자페이지 > 공지관리 > 공지보기 > 수정
//    @GetMapping("/admin_notice_update")
    @PostMapping("/admin_notice_update")
    public String admin_notice_update(@ModelAttribute NoticeDTO noticeDTO, Model model) {
        model.addAttribute("notice",noticeDTO);
        model.addAttribute("moons",adminMoonService.findAll());
        return "adminad/admin_notice_update";
    }

    //    관리자페이지 > 공지관리 > 공지보기 > 수정 (완료)
    @PostMapping("/admin_notice_update_ok")
    public String admin_notice_update_ok(@Valid NoticeDTO noticeDTO, @RequestParam("file") MultipartFile file, Model model) throws IOException {
        noticeDTO.setNoticeContent(noticeDTO.getNoticeContent().replace("\r\n", "<br>"));
        noticeService.update(noticeDTO, file);
        model.addAttribute("notice", noticeDTO);
        model.addAttribute("moons",adminMoonService.findAll());
        return "adminad/admin_notice_look";
    }


    //    관리자페이지 > 공지관리 > 공지보기 > 삭제
    @Transactional
    @PostMapping("/admin_notice_delete")
    public String admin_notice_delete(@ModelAttribute NoticeDTO noticeDTO, Model model) {
        noticeService.deleteByNoticeId(noticeDTO.getNotice_id());
        model.addAttribute("notice",noticeDTO);
        model.addAttribute("moons",adminMoonService.findAll());
        return "redirect:/admin_notice";
    }

    //    관리자페이지 > 공지관리 > 공지검색
    @RequestMapping("/admin_notice_search")
    public String notice_search2(@RequestParam String notice_keyword, Model model) {
        List<NoticeDTO> importantDTOs = noticeService.searchIm(notice_keyword);
        List<NoticeDTO> normalDTOs = noticeService.searchNo(notice_keyword);
        List<FaqDTO> faqDTOS = faqService.findAll();

        model.addAttribute("importants",importantDTOs); //중요공지 검색결과
        model.addAttribute("normals",normalDTOs); //일반공지 검색결과
        model.addAttribute("keyword",notice_keyword); //검색한 키워드
        model.addAttribute("faqList",faqDTOS); //같은 화면에 담을 faq리스트

        model.addAttribute("moons",adminMoonService.findAll());

        return "/adminad/admin_notice_search";
    }

}
