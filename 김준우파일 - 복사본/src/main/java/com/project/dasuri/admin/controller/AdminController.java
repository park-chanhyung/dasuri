package com.project.dasuri.admin.controller;//package com.project.dasuri.controller;

import com.project.dasuri.admin.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class AdminController {

//    생성자 주입
    private final NoticeService noticeService;

//    관리자 페이지 (메인)
    @RequestMapping("/admin")
    public String admin_home() {
        return "/adminad/admin_home";
    }

    //  관리자 페이지 > 회원관리 (회원리스트)
    @RequestMapping("/admin_mem")
    public String admin_mem() {
        return "/adminad/admin_mem";
    }

    //  관리자페이지 > 고객센터 (문의글 리스트)
    @RequestMapping("/admin_moon")
    public String admin_moon() {
        return "/adminad/admin_moon";
    }

    //  관리자페이지 > 고객센터 > 문의글보기
    @RequestMapping("/admin_moon_view")
    public String admin_moon_view() {
        return "adminad/admin_moon_view";
    }

    //    관리자페이지 > 공지관리 > FAQ 올리기 (작성 폼)
    @RequestMapping("/admin_faq_write")
    public String admin_faq_write() {
        return "adminad/admin_faq_write";
    }
}
