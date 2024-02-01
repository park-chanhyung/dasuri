package com.project.dasuri.mypage.controller;

import com.project.dasuri.member.dto.ProDTO;
import com.project.dasuri.mypage.service.ProMyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ProMyPageController {

    private final ProMyPageService proMyPageService;
    private static final Logger logger = LoggerFactory.getLogger(ProMyPageController.class);

    @GetMapping("/promypage")
    public String promypage(@RequestParam("proId") String proId, Model model) {
        logger.info("Received proId: {}", proId);
//    public String promypage() {
        ProDTO proDTO = proMyPageService.findById(proId);
        model.addAttribute("promodify", proDTO);
        return "/mypage/promypage";
    }
}
//    @PostMapping("/promypage")
//    public String processPromypage(@RequestParam("proId") String proId, Model model) {
//        // 처리 로직 추가
//        return "/mypage/promypage";
//    }
//}