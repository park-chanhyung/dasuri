package com.project.dasuri.member.controller;

import com.project.dasuri.member.dto.UserDTO;
import com.project.dasuri.member.service.ProService;
import com.project.dasuri.member.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
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

}
