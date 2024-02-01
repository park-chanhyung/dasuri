package com.project.dasuri.member.controller;

import com.project.dasuri.member.dto.ProDTO;
import com.project.dasuri.member.service.ProService;
import com.project.dasuri.member.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProController {
    private ProService proService;
    @Autowired
    public ProController(ProService proService){
        this.proService = proService;
    }

    @PostMapping("/pro_signup")
    public String pro_signup(@ModelAttribute ProDTO proDTO)
    {
        System.out.println("MemberController.pro_signup");
        System.out.println("proDTO = " + proDTO);
        proService.pro_signup(proDTO);

        return "redirect:login";
    }

    @RequestMapping("/pro_login")
    public String pro_login() {

        return "/login/pro_login";
    }
}
