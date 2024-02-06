package com.project.dasuri.mypage.controller;

import com.project.dasuri.admin.dto.MoonDTO;
import com.project.dasuri.admin.service.Admin_MoonService;
import com.project.dasuri.member.dto.UserDTO;
import com.project.dasuri.member.service.UserService;
import com.project.dasuri.mypage.service.UserMyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

//@Slf4j
@Controller
@RequiredArgsConstructor
public class UserPageController {

    private final UserService userService;
    private final Admin_MoonService adminMoonService;

//
    @PostMapping("/userpage")
    public String userpage(Model model) {
        // Spring Security를 통해 로그인한 사용자의 정보를 가져옴
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 사용자 아이디 추출
        String userId = authentication.getName();

//    public String usermypage() {

        UserDTO userdto = UserDTO.toUserDTO(userService.findByUserId(userId));
        model.addAttribute("usermodify", userdto);
        return "usermypage/userpage";
    }

    @PostMapping("/usermoonpage")
    public String usermoonpage(Model model) {
        // Spring Security를 통해 로그인한 사용자의 정보를 가져옴
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 사용자 아이디 추출
        String userId = authentication.getName();

        List<MoonDTO> moonDTOS = adminMoonService.findByMoonUserId(userId);
        model.addAttribute("usermoni", moonDTOS);
        return "usermypage/usermoonpage";
    }
//    // 이미지 등록하면 메인화면 이동
//    @PostMapping("/userpage_img_ok")
//    public String userpage_img_ok(@Valid UserPageDTO userPageDTO, BindingResult bindingResult, MultipartFile file) throws IOException {
//        if (bindingResult.hasErrors()){
//            return "usermypage/userpage";
//        }
//        this.userMyPageService.create(userPageDTO.getId(), file);
//        return "redirect:/index";
//    }

}

