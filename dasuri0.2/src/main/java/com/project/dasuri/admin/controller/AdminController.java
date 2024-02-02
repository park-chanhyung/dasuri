package com.project.dasuri.admin.controller;//package com.project.dasuri.controller;

import com.project.dasuri.admin.dto.MoonDTO;
import com.project.dasuri.admin.service.Admin_MoonService;
import com.project.dasuri.admin.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final Admin_MoonService adminMoonService;

//    관리자 페이지 (메인)
    @RequestMapping("/admin")
    public String admin_home(Model model) {
        List<MoonDTO> moonDTOS = adminMoonService.findAll();
        model.addAttribute("moons",moonDTOS);
        return "/adminad/admin_home";
    }

    //  관리자페이지 > 고객센터 > 커뮤니티
    @RequestMapping("/admin_community")
    public String admin_community(Model model) {
        List<MoonDTO> moonDTOS = adminMoonService.findAll();
        model.addAttribute("moons",moonDTOS);
        return "adminad/admin_community";
    }

}
