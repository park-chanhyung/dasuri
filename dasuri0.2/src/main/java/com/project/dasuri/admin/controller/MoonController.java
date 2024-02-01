package com.project.dasuri.admin.controller;

import com.project.dasuri.admin.dto.MoonDTO;
import com.project.dasuri.admin.dto.NoticeDTO;
import com.project.dasuri.admin.service.MoonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

//------------------------------- 1:1 문의하기 컨트롤러 ------------------------------------------
@Controller
@RequiredArgsConstructor
@Slf4j
public class MoonController {
    private final MoonService moonService;

    //    메인 - 고객센터 - 1:1문의하기 (입력 폼) - 회원 id와 role 가지고 감
    @RequestMapping("/center_question")
    public String center_question(Model model){

    //세션 현재 사용자 id값
    String id = SecurityContextHolder.getContext().getAuthentication().getName();

    //현재 로그인한 사용자의 role값
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
    Iterator<? extends GrantedAuthority> iter = authorities.iterator();
    GrantedAuthority auth = iter.next();
    String role = auth.getAuthority();

        model.addAttribute("id", id);
        model.addAttribute("role",role);

        return "/list/service/center_question";
    }

    //    메인 - 고객센터 - 1:1문의하기 (등록 완료)
    @RequestMapping("/center_question_ok")
    public String center_question_ok(@Valid MoonDTO moonDTO, @RequestParam("file") MultipartFile file) throws IOException {
//        고객문의글의 줄바꿈 형식 유지
        moonDTO.setMoonQuestion(moonDTO.getMoonQuestion().replace("\r\n","<br>"));

        moonService.questionSave(moonDTO,file);
        return "/list/service/center_question_ok";
    }


}
