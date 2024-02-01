package com.project.dasuri.admin.controller;

import com.project.dasuri.admin.dto.MoonDTO;
import com.project.dasuri.admin.service.Admin_MoonService;
import com.project.dasuri.admin.service.MoonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminMoonController {
    private final Admin_MoonService adminMoonService;

    //  관리자페이지 > 고객센터 (문의글 리스트)
    @RequestMapping("/admin_moon")
    public String admin_moon(Model model) {
        List<MoonDTO> moonDTOS = adminMoonService.findAll();
        model.addAttribute("moons",moonDTOS);
        return "/adminad/admin_moon";
    }



    //  관리자페이지 > 고객센터 > 문의글보기
    @RequestMapping("/admin_moon_view")
    public String admin_moon_view() {
        return "adminad/admin_moon_view";
    }
}
