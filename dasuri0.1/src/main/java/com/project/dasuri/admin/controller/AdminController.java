package com.project.dasuri.controller;

import com.project.dasuri.dto.FaqDTO;
import com.project.dasuri.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class AdminController {

//    생성자 주입
//    private final AdminService service;

    @RequestMapping("/admin")
    public String admin_home() {

        return "/adminad/admin_home";
    }
    //  관리자 페이지 : 회원관리
    @RequestMapping("/admin_mem")
    public String admin_mem() {

        return "/adminad/admin_mem";
    }
    //  관리자 페이지 : 공지관리
    @RequestMapping("/admin_notice")
    public String admin_notice() {

        return "/adminad/admin_notice";
    }
    //  관리자 페이지 : 고객센터
    @RequestMapping("/admin_moon")
    public String admin_moon() {

        return "/adminad/admin_moon";
    }
    //  관리자 페이지 : 문의글 보기
    @RequestMapping("/admin_moon_view")
    public String admin_moon_view() {

        return "adminad/admin_moon_view";
    }
    //    관리자 페이지 : 자주찾는질문 작성폼
    @RequestMapping("/admin_faq_write")
    public String admin_faq_write() {

        return "adminad/admin_faq_write";
    }

//    관리자 페이지 : 자주찾는질문 등록
    @RequestMapping("/admin_faq_write_ok")
    public String admin_faq_write_ok(@ModelAttribute FaqDTO faqDTO) {

        return "adminad/admin_faq_write_ok";
    }


}
