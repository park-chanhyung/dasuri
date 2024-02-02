package com.project.dasuri.admin.controller;

import com.project.dasuri.admin.dto.MoonDTO;
import com.project.dasuri.admin.service.Admin_MoonService;
import com.project.dasuri.admin.service.MoonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminMoonController {
    private final Admin_MoonService adminMoonService;
    private final MoonService moonService;

    //  관리자페이지 > 고객센터 (문의글 리스트)
    @RequestMapping("/admin_moon")
    public String admin_moon(Model model) {
        List<MoonDTO> moonDTOS = adminMoonService.findAll();
        model.addAttribute("moons",moonDTOS);
        return "/adminad/admin_moon";
    }

    //  관리자페이지 > 고객센터 (문의글 조회)
    @PostMapping("/admin_moon_view")
    public String admin_moon(@RequestParam Long moonPkId, Model model) {
        MoonDTO moonDTO = adminMoonService.findByMoonPkId(moonPkId);
        moonDTO.setMoonQuestion(moonDTO.getMoonQuestion().replace("<br>","\n"));
        model.addAttribute("moon", moonDTO);
        model.addAttribute("moons",adminMoonService.findAll());
        return "/adminad/admin_moon_view";
    }

    //  관리자페이지 > 고객센터 (문의글 답변 폼)
    @PostMapping("/admin_moon_answer")
    public String admin_moon_answer(@RequestParam Long moonPkId, Model model) {
        MoonDTO moonDTO = adminMoonService.findByMoonPkId(moonPkId);
        model.addAttribute("moon", moonDTO);
        model.addAttribute("moons",adminMoonService.findAll());
        return "/adminad/admin_moon_answer";
    }

    //  관리자페이지 > 고객센터 (문의글 답변 완료)
    @PostMapping("/admin_moon_answer_ok")
    public String admin_moon_answer(@ModelAttribute("moonPkId") Long moonPkId, @RequestParam("moonAnswer") String moonAnswer, Model model) throws IOException {
        MoonDTO moonDTO = adminMoonService.findByMoonPkId(moonPkId);
        //        답변저장
        moonDTO.setMoonAnswer(moonAnswer);
        moonService.answerSave(moonDTO);
        model.addAttribute("moon", moonDTO);
        model.addAttribute("moons",adminMoonService.findAll());
        // 답변 작성 완료 후 리다이렉션할 페이지로 이동
        return "/adminad/admin_moon_answer_ok";
//        return "/adminad/admin_moon_view";
    }

    //  관리자페이지 > 고객센터 (문의글 검색)
    @RequestMapping("/admin_moon_search")
    public String admin_moon_search(@RequestParam String moon_keyword, Model model) {

//        검색결과
        List<MoonDTO> moonDTOSearch = adminMoonService.moonSearch(moon_keyword);
        model.addAttribute("moonsSearch",moonDTOSearch);
        model.addAttribute("keyword",moon_keyword); //검색한 키워드
//        푸터용
        List<MoonDTO> moonDTOS = adminMoonService.findAll();
        model.addAttribute("moons",moonDTOS);
        return "/adminad/admin_moon_search";
    }
}
