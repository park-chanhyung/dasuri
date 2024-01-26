package com.project.dasuri.admin.controller;

import com.project.dasuri.admin.dto.NoticeDTO;
import com.project.dasuri.admin.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class NoticeController {

//    생성자 주입
    private final NoticeService noticeService;

    //  관리자페이지 > 공지관리 (공지&FAQ 리스트)
    @RequestMapping("/admin_notice")
    public String admin_notice(Model model) {
        List<NoticeDTO> noticeDTOS = noticeService.findAll();
        model.addAttribute("noticeList",noticeDTOS);
        return "/adminad/admin_notice";
    }


    //    관리자페이지 > 공지관리 > 공지올리기 (작성 폼)
    @RequestMapping("/admin_notice_write")
    public String admin_notice_write() {
        return "adminad/admin_notice_write";
    }

    //    관리자페이지 > 공지관리 > 공지올리기 (작성 완료)
    @RequestMapping("/admin_notice_write_ok")
    public String admin_notice_write_ok(@ModelAttribute NoticeDTO noticeDTO) {
        noticeService.save(noticeDTO);
        return "adminad/admin_notice_write_ok";
    }

    //    관리자페이지 > 공지관리 > 공지보기 (내용보기 및 수정삭제)
    @PostMapping("/admin_notice_view")
    public String admin_notice_view(@RequestParam Long id,Model model) {
        NoticeDTO noticeDTO = noticeService.findByNoticeId(id);
        model.addAttribute("notice",noticeDTO);
        return "adminad/admin_notice_look";
    }

    //    관리자페이지 > 공지관리 > 공지보기 > 수정
    @PostMapping("/admin_notice_update")
    public String admin_notice_update(@ModelAttribute NoticeDTO noticeDTO, Model model) {
        model.addAttribute("notice",noticeDTO);
        return "adminad/admin_notice_update";
    }

    //    관리자페이지 > 공지관리 > 공지보기 > 수정 (완료)
    @PostMapping("/admin_notice_update_ok")
    public String admin_notice_update_ok(@ModelAttribute NoticeDTO noticeDTO, Model model) {
        noticeService.update(noticeDTO);
        model.addAttribute("notice",noticeDTO);
        return "redirect:adminad/admin_notice";
    }

}
