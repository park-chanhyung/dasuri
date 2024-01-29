package com.project.dasuri.member.controller;

import com.project.dasuri.member.dto.ProDTO;
import com.project.dasuri.member.dto.UserDTO;
import com.project.dasuri.member.service.ProService;
import com.project.dasuri.member.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class MemberController {

    @GetMapping("/login")
    public String login() {

        return "/login/login";
    }

    @RequestMapping("/sign_up")
    public String sign_up() {

        return "/login/sign_up";
    }

    @RequestMapping("/pro_signup")
    public String pro_signupP() {

        return "/login/pro_signup";
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
