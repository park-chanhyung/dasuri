package com.project.dasuri.member.controller;

import com.project.dasuri.member.dto.UserDTO;
import com.project.dasuri.member.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {
    private UserService userService;

    @Autowired
    public MemberController(UserService userService){
        this.userService = userService;
    }
    @RequestMapping("/login")
    public String login2() {

        return "/login/login";
    }

    @RequestMapping("/user_login")
    public String user_login() {

        return "/login/user_login";
    }
    @PostMapping("/sign_up")
    public String sign_up(@ModelAttribute UserDTO userDTO)
    {
        System.out.println("MemberController.sign_up");
        System.out.println("userDTO = " + userDTO);
        userService.sign_up(userDTO);

        return "redirect:user_login";
    }


    @RequestMapping("/pro_login")
    public String pro_login() {

        return "/login/pro_login";
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
