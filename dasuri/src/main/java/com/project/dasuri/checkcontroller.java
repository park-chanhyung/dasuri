package com.project.dasuri;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class checkcontroller {
    @RequestMapping("/index")
    public String login() {

        return "index";
    }
    //  관리자 페이지 : 홈
    @RequestMapping("/a")
    public String admin_home() {

        return "admin_home";
    }
    //  관리자 페이지 : 회원관리
    @RequestMapping("/admin_mem")
    public String admin_mem() {

        return "admin_mem";
    }
    //  관리자 페이지 : 공지관리
    @RequestMapping("/admin_notice")
    public String admin_notice() {

        return "admin_notice";
    }
    //  관리자 페이지 : 고객센터
    @RequestMapping("/admin_moon")
    public String admin_moon() {

        return "admin_moon";
    }
}
