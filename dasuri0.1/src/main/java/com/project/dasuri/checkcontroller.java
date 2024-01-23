package com.project.dasuri;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class checkcontroller {
    @RequestMapping("/index")
    public String login() {
//    public String index() {

        return "index";
    }

    @RequestMapping("/notice")
    public String notice() {
//    public String index() {

        return "/list/notice";
    }

    @RequestMapping("/community")
    public String community() {
//    public String index() {

        return "/list/community";
    }

    @RequestMapping("/proinfo")
    public String proinfo() {
//    public String index() {

        return "/list/proinfo";
    }

    @RequestMapping("/servicecenter")
    public String servicecenter() {
//    public String index() {

        return "/list/servicecenter";
    }

    //  관리자 페이지 : 홈
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
    @RequestMapping("/admin_moon_view")
    public String admin_moon_view() {

        return "adminad/admin_moon_view";
    }
    @RequestMapping("/login")
    public String login2() {

        return "/login/login";
    }

    @RequestMapping("/register")
    public String register() {

        return "/login/register";
    }


    @RequestMapping("/register_expert")
    public String register_expert() {

        return "/login/register_expert";
    }

    @RequestMapping("/find_user")
    public String find_user() {

        return "/login/find_user";
    }
    @RequestMapping("/my_profile")
    public String my_profile() {

        return "/login/my_profile";
    }
}
