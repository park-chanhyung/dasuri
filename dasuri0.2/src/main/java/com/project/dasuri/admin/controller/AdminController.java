package com.project.dasuri.admin.controller;//package com.project.dasuri.controller;

import com.project.dasuri.admin.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class AdminController {

//    관리자 페이지 (메인)
    @RequestMapping("/admin")
    public String admin_home() {
        return "/adminad/admin_home";
    }

    //  관리자페이지 > 고객센터 > 커뮤니티
    @RequestMapping("/admin_community")
    public String admin_community() {
        return "adminad/admin_community";
    }

}
