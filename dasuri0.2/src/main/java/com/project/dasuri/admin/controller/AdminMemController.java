package com.project.dasuri.admin.controller;

import com.project.dasuri.admin.service.Admin_MoonService;
import com.project.dasuri.admin.service.Admin_ProService;
import com.project.dasuri.admin.service.Admin_UserService;
import com.project.dasuri.member.dto.ProDTO;
import com.project.dasuri.member.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminMemController {
    private final Admin_UserService userService;
    private final Admin_ProService proService;
    private final Admin_MoonService adminMoonService;

    //  관리자 페이지 > 회원관리 (회원리스트 > 고객리스트 기본)
    @RequestMapping("/admin_mem")
    public String admin_mem(Model model) {
        List<UserDTO> userDTOS = userService.findAll();
        model.addAttribute("users",userDTOS);
        model.addAttribute("moons",adminMoonService.findAll());
        return "/adminad/admin_mem_user";
    }
    //  관리자 페이지 > 회원관리 (회원리스트 > 기사리스트)
    @RequestMapping("/admin_mem_pro")
    public String admin_mem_pro(Model model) {
        List<ProDTO> proDTOS = proService.findAll();
        model.addAttribute("pros",proDTOS);
        model.addAttribute("moons",adminMoonService.findAll());
        return "/adminad/admin_mem_pro";
    }

    //  관리자 페이지 > 회원관리 (회원리스트 > 회원보기)
    @PostMapping("/admin_mem_user_view")
    public String admin_mem_user_view(@RequestParam String userId, Model model) {
        UserDTO userDTO = userService.findByUserId(userId);
        model.addAttribute("user",userDTO);
        model.addAttribute("moons",adminMoonService.findAll());
        return "/adminad/admin_mem_user_view";
    }

    //  관리자 페이지 > 회원관리 (회원리스트 > 기사보기)
    @PostMapping("/admin_mem_pro_view")
    public String admin_mem_pro_view(@RequestParam String proId, Model model) {
        ProDTO proDTO = proService.findByProId(proId);
        model.addAttribute("pro",proDTO);
        model.addAttribute("moons",adminMoonService.findAll());
        return "/adminad/admin_mem_pro_view";
    }


}
