package com.project.dasuri.mypage.controller;

import com.project.dasuri.member.dto.UserDTO;
import com.project.dasuri.mypage.service.UserMyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@Slf4j
@Controller
@RequiredArgsConstructor
public class UserPageController {

    private final UserMyPageService userMyPageService;

    @PostMapping("/usermypage")
    public String usermypage(Model model) {
        // Spring Security를 통해 로그인한 사용자의 정보를 가져옴
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 사용자 아이디 추출
        String userId = authentication.getName();

//    public String usermypage() {

        UserDTO userDTO = userMyPageService.findById(userId);
        model.addAttribute("usermodify", userDTO);
        return "mypage/usermypage";
    }
}
//    @PostMapping("/promypage")
//    public String processPromypage(@RequestParam("proId") String proId, Model model) {
//        // 처리 로직 추가
//        return "/mypage/promypage";
//    }
//}