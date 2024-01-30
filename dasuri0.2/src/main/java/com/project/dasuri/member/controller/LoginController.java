package com.project.dasuri.member.controller;

import com.project.dasuri.member.dto.UserDTO;
import com.project.dasuri.member.entity.UserEntity;
import com.project.dasuri.member.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class LoginController {

//    @GetMapping("/index")
//    public String index(Model model, Principal principal){
//        String userId = principal.getName();
//        return "index";
//    }
//
//    @GetMapping("/mainpage")
//    public String mainpage(Model model,Principal principal){
//        int maxroomNum = mysqlChatService.findByMaxroomNum();
//        String userid = principal.getName();
//        SiteUser user =userService.mappingId(userid);
//
//        model.addAttribute("user", user);
//        model.addAttribute("maxroomNum", maxroomNum);
//        return "mainpage";
//    }
}
