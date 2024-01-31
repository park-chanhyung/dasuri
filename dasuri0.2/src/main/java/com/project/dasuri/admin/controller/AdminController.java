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

    //  관리자페이지 > 고객센터 > 커뮤니티
    @RequestMapping("/admin_community")
    public String admin_community() {
        return "adminad/admin_community";
    }

}
