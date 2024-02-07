package com.project.dasuri.mypage.controller;

import com.project.dasuri.admin.dto.MoonDTO;
import com.project.dasuri.admin.service.Admin_MoonService;
import com.project.dasuri.member.dto.ProDTO;
import com.project.dasuri.member.dto.UserDTO;
import com.project.dasuri.member.service.UserService;
import com.project.dasuri.mypage.service.UserMyPageService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

//@Slf4j
@Controller
@RequiredArgsConstructor
public class UserPageController {

    private final UserService userService;
    private final UserMyPageService userMyPageService;
    private final Admin_MoonService adminMoonService;

    //
    @PostMapping("/userpage")
    public String userpage(Model model) {
        System.out.println("유저회원정보수정 진입");
        // Spring Security를 통해 로그인한 사용자의 정보를 가져옴
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 사용자 아이디 추출
        String userId = authentication.getName();

        UserDTO userdto = UserDTO.toUserDTO(userService.findByUserId(userId));
        model.addAttribute("userDTO", userdto);
        return "usermypage/userpage";
    }

    @PostMapping("/usermoonpage")
    public String usermoonpage(Model model) {
        System.out.println("무니가 나왔나??");
        // Spring Security를 통해 로그인한 사용자의 정보를 가져옴
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 사용자 아이디 추출
        String userId = authentication.getName();

        List<MoonDTO> moonDTOS = adminMoonService.findByMoonUserId(userId);
        model.addAttribute("usermoni", moonDTOS);
        return "usermypage/usermoonpage";
    }

    //유저회원정보 변경 메소드
    @PostMapping("/userupdate")
    public String update(@Valid @ModelAttribute UserDTO userDTO, BindingResult br, Model model, MultipartFile file, HttpServletRequest request, RedirectAttributes redirectAttributes) throws IOException {
        System.out.println("유저 컨트롤러 메소드 업데이트 -> 서비스로 가야함");
        System.out.println("userDTO = " + userDTO + ", br = " + br + ", model = " + model + ", file = " + file + ", request = " + request + ", redirectAttributes = " + redirectAttributes);
        if(br.hasErrors()){
            //회원정보 수정 실패시 기존 입력값 유지
            model.addAttribute("userDTO",userDTO);
            return "redirect:/userpage";
        }else {
            boolean isPasswordChanged = userMyPageService.update(userDTO, file);
            System.out.println("비밀번호를 변경하면 true, 변경 안하면 false가 보여야함 ===>"+isPasswordChanged);
            if (isPasswordChanged) {
                // 비밀번호가 변경되었으면 현재 세션 무효화
                SecurityContextHolder.getContext().setAuthentication(null);
                request.getSession().invalidate();
                redirectAttributes.addFlashAttribute("msg", "비밀번호가 변경되었습니다. 다시 로그인해 주세요.");
                return "redirect:/login"; // 로그인 페이지로 리다이렉션
            } else {
                // 비밀번호 변경이 없으면 프로필 페이지로 리다이렉션
                return "redirect:/proprofile";
            }
        }
    }


}