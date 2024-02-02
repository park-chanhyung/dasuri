package com.project.dasuri.member.controller;

import com.project.dasuri.member.dto.ProDTO;
import com.project.dasuri.member.dto.UserDTO;
import com.project.dasuri.member.service.ProService;
import com.project.dasuri.member.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProController {
    private ProService proService;
    private UserService userService;

    @Autowired
    public ProController(ProService proService, UserService userService) {
        this.proService = proService;
        this.userService = userService;
    }

    @GetMapping("/pro_signup")
    public String pro_signupP(ProDTO proDTO) {

        return "/login/pro_signup";
    }

    @PostMapping("/pro_signup")
    public String pro_signup(@Valid @ModelAttribute ProDTO proDTO, BindingResult br, Model model) {
        System.out.println("MemberController.pro_signup");
        System.out.println("proDTO = " + proDTO);
        model.addAttribute("proDTO", proDTO);

        if (br.hasErrors()) {
            //회원가입 실패시 기존 입력값 유지
            model.addAttribute("proDTO", proDTO);
            return "/login/pro_signup";
        } else {
            // 유효성 검사에 성공한 경우
            // 아이디 중복 확인
            boolean isDuplicate = userService.isUserIdDuplicate(proDTO.getProId());
            if (isDuplicate) {
                // 아이디가 중복된 경우
                model.addAttribute("proDTO", proDTO);
                model.addAttribute("errorMessage", "이미 사용 중인 아이디입니다.");
                return "/login/pro_signup";
            } else {
                // 아이디가 중복되지 않은 경우
                proService.pro_signup(proDTO);
                return "redirect:login";
            }
        }

    }
}
