package com.project.dasuri.member.controller;

import com.project.dasuri.member.dto.UserDTO;
import com.project.dasuri.member.entity.UserEntity;
import com.project.dasuri.member.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.Collection;
import java.util.Iterator;

@Controller
public class LoginController {
//
//    @PostMapping("/loginProc")
//    public String loginProcess() {
//        System.out.println("@#@#@#@#@# /loginProc 탔음");
//        return "redirect:/index"; // 로그인 성공 후 이동할 페이지
//    }
}
