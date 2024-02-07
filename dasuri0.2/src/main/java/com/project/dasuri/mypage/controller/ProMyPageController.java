package com.project.dasuri.mypage.controller;

import com.project.dasuri.member.dto.ProDTO;
import com.project.dasuri.member.entity.ProEntity;
import com.project.dasuri.member.service.ProService;
import com.project.dasuri.member.service.UserService;
import com.project.dasuri.mypage.service.ProMyPageService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ProMyPageController {

    private final ProService proService;
    private final ProMyPageService proMyPageService;

    private final UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(ProMyPageController.class);

    @PostMapping("/propage")
    public String promypage(Model model) {
        System.out.println("기사회원정보수정 진입");
        // Spring Security를 통해 로그인한 사용자의 정보를 가져옴
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 사용자 아이디 추출
        String proId = authentication.getName();
//        ProEntity pro = proService.findByProId(proId);
        ProDTO prodto = ProDTO.toProDTO(proService.findByProId(proId));
//        model.addAttribute("promodify", prodto);
        model.addAttribute("proDTO", prodto);
        return "promypage/propage";
    }

    //기사회원정보 메소드
    @PostMapping("/proupdate")
    public String update(@Valid @ModelAttribute ProDTO proDTO, BindingResult br, Model model, MultipartFile file, HttpServletRequest request, RedirectAttributes redirectAttributes) throws IOException {
        System.out.println("컨트롤러 메소드 업데이트 -> 서비스로 가야함");
        System.out.println("proDTO = " + proDTO + ", br = " + br + ", model = " + model);
        if(br.hasErrors()){
            //회원정보 수정 실패시 기존 입력값 유지
            model.addAttribute("proDTO",proDTO);
            return "redirect:/propage";
        }else {
            boolean isPasswordChanged = proMyPageService.update(proDTO, file);
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
//    @GetMapping("/promypage")
//    public String promypage(@RequestParam("proId") String proId, Model model) {
//        logger.info("Received proId: {}", proId);
////    public String promypage() {
//        ProDTO proDTO = proMyPageService.findById(proId);
//        model.addAttribute("promodify", proDTO);
//        return "/mypage/promypage";
//    }
//}
//    @PostMapping("/promypage")
//    public String processPromypage(@RequestParam("proId") String proId, Model model) {
//        // 처리 로직 추가
//        return "/mypage/promypage";
//    }
//}