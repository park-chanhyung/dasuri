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
    private UserService userService;
    private ProService proService;

    @Autowired
    public MemberController(UserService userService, ProService proService){

        this.userService = userService;
        this.proService = proService;
    }


    @GetMapping("/login")
    public String login() {

        return "/login/login";
    }

    @RequestMapping("/sign_up")
    public String sign_up() {

        return "/login/sign_up";
    }

    @RequestMapping("/user_signup")
    public String user_signupP() {

        return "/login/user_signup";
    }

    //    @PostMapping("/user_signup")
//    public String user_signup( @ModelAttribute UserDTO userDTO)
//    {
//        System.out.println("MemberController.sign_up");
//        System.out.println("userDTO = " + userDTO);
//        userService.sign_up(userDTO);
//
//        return "redirect:login";
//    }
    @PostMapping("/user_signup")
    public String user_signup(@Valid @ModelAttribute UserDTO userDTO, BindingResult br, Model model)
    {
        System.out.println("MemberController.sign_up");
        System.out.println("userDTO = " + userDTO);
        if(br.hasErrors()){
            return "/login/user_signup";
        }else{
            model.addAttribute(userDTO.getUserName());
            userService.user_signup(userDTO);
            return "redirect:login";
        }
    }

    @RequestMapping("/pro_signup")
    public String pro_signupP() {

        return "/login/pro_signup";
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

    @RequestMapping("/find_user")
    public String find_user() {

        return "/login/find_user";
    }
    @RequestMapping("/my_profile")
    public String my_profile() {

        return "/login/my_profile";
    }
}
