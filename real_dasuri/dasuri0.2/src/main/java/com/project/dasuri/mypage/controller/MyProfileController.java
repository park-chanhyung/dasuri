package com.project.dasuri.mypage.controller;

import com.project.dasuri.member.dto.ProDTO;
import com.project.dasuri.member.dto.UserDTO;
import com.project.dasuri.member.entity.ProEntity;
import com.project.dasuri.member.entity.UserEntity;
import com.project.dasuri.member.service.ProService;
import com.project.dasuri.member.service.UserService;
import com.project.dasuri.mypage.service.ProMyPageService;
import com.project.dasuri.mypage.service.UserMyPageService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class MyProfileController {
    private final UserService userService;
    private final ProService proService;
    public MyProfileController(UserService userService, ProService proService) {
        this.userService = userService;
        this.proService = proService;
    }

    @GetMapping("/myprofile")
    public String myProfileRedirect() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER"))) {
            // 유저의 경우 유저 프로필 페이지로 리다이렉트
            return "redirect:/userprofile";
        } else if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_PRO"))) {
            // 프로의 경우 프로 프로필 페이지로 리다이렉트
            return "redirect:/proprofile";
        } else {
            // 다른 역할이나 인증되지 않은 경우에는 예외처리 또는 다른 로직을 추가할 수 있습니다.
            return "redirect:/error";
        }
    }
    @GetMapping("/userprofile")
//    public String userprofile(@RequestParam("userId")String userId, Model model){
    public String userprofile(Model model, Principal principal){
        System.out.println("유저 프로필 확인");
        // Spring Security를 통해 로그인한 사용자의 정보를 가져옴
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 사용자 아이디 추출
        String userId = authentication.getName();
        UserEntity user = userService.findByUserId(userId);
        model.addAttribute("userpf", user);
        return "usermypage/userprofile";
    }

//    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/proprofile")
    public String proprofile(Model model, Principal principal){
        System.out.println("기사 프로필 확인");
        // Spring Security를 통해 로그인한 사용자의 정보를 가져옴
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 사용자 아이디 추출
//        String proId = authentication.getName();
        String proId = principal.getName();
        ProEntity pro = proService.findByProId(proId);
        model.addAttribute("propf", pro);
        return "promypage/proprofile";
    }

    @GetMapping("/proprofile_P")
    public String proprofile(){
        return "promypage/proprofile";
    }

}

