package com.project.dasuri.admin.controller;

import com.project.dasuri.admin.dto.MoonDTO;
import com.project.dasuri.admin.dto.NoticeDTO;
import com.project.dasuri.admin.service.Admin_MoonService;
import com.project.dasuri.admin.service.MoonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminMoonController {
    private final Admin_MoonService adminMoonService;
    private final MoonService moonService;

    //  관리자페이지 > 고객센터 (문의글 리스트)
    @RequestMapping("/admin_moon")
    public String admin_moon(@PageableDefault(page = 1) Pageable pageable, Model model) {

        // 문의글 페이징 (고유번호 내림차순)
        Page<MoonDTO> moonDTOS = adminMoonService.admin_paging(pageable); // 아이디, 회원유형, 구분, 제목, 작성일, 상태, 고유번호

        // 현재 페이지에서 앞 뒤 갯수
        int blockLimit = 5;
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = ((startPage + blockLimit - 1) < moonDTOS.getTotalPages()) ? startPage + blockLimit - 1 : moonDTOS.getTotalPages();

        model.addAttribute("moons",moonDTOS);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "/adminad/admin_moon";
    }

    //  관리자페이지 > 고객센터 (문의글 조회)
    @GetMapping("/admin_moon_view/{moonPkId}")
    public String admin_moon_view(@PathVariable Long moonPkId, Model model) {
        MoonDTO moonDTO = adminMoonService.findByMoonPkId(moonPkId);
        moonDTO.setMoonQuestion(moonDTO.getMoonQuestion().replace("<br>","\n"));
        model.addAttribute("moon", moonDTO);

        model.addAttribute("moons", adminMoonService.admin_paging(PageRequest.of(1, 7))); // 푸터용
        return "/adminad/admin_moon_view";
    }

    //  관리자페이지 > 고객센터 (문의글 답변 폼)
    @GetMapping("/admin_moon_answer/{moonPkId}")
    public String admin_moon_answer(@PathVariable Long moonPkId, Model model) {
        MoonDTO moonDTO = adminMoonService.findByMoonPkId(moonPkId);
        model.addAttribute("moon", moonDTO);
        model.addAttribute("moons", adminMoonService.admin_paging(PageRequest.of(1, 7))); // 푸터용
        return "/adminad/admin_moon_answer";
    }

    //  관리자페이지 > 고객센터 (문의글 답변 완료)
    @PostMapping("/admin_moon_answer_ok/{moonPkId}")
    public String admin_moon_answer(@PathVariable Long moonPkId, String moonAnswer, Model model) throws IOException {
        MoonDTO moonDTO = adminMoonService.findByMoonPkId(moonPkId);
        //        답변저장
        moonDTO.setMoonAnswer(moonAnswer);
        moonService.answerSave(moonDTO);
        model.addAttribute("moon", moonDTO);
    model.addAttribute("moons", adminMoonService.admin_paging(PageRequest.of(1, 7))); // 푸터용
    // 답변 작성 완료 후 리다이렉션할 페이지로 이동
    return "/adminad/admin_moon_answer_ok";
    }

    //  관리자페이지 > 고객센터 (문의글 검색)
    @RequestMapping("/admin_moon_search")
    public String admin_moon_search(@RequestParam String moon_keyword, @PageableDefault(page = 1) Pageable pageable, Model model) {

//        검색결과 페이징
        Page<MoonDTO> moonDTOSearch = adminMoonService.moonSearch(moon_keyword, pageable);

        // 현재 페이지에서 앞 뒤 갯수
        int blockLimit = 5;
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = ((startPage + blockLimit - 1) < moonDTOSearch.getTotalPages()) ? startPage + blockLimit - 1 : moonDTOSearch.getTotalPages();

        model.addAttribute("moonsSearch",moonDTOSearch);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        model.addAttribute("keyword",moon_keyword); //검색한 키워드
        model.addAttribute("moons", adminMoonService.admin_paging(PageRequest.of(1, 7))); // 푸터용
        return "/adminad/admin_moon_search";
    }

    //  관리자페이지 > 고객센터 > 상태 필터 (페이징)
    @GetMapping("/admin_moon_status/{status}")
    public String admin_moon_status(@PathVariable String status, @PageableDefault(page = 1) Pageable pageable, Model model) {

//        상태에 따른 필터링 페이징
        Page<MoonDTO> moonStatus = adminMoonService.moonStatus(status, pageable);

        // 현재 페이지에서 앞 뒤 갯수
        int blockLimit = 5;
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = ((startPage + blockLimit - 1) < moonStatus.getTotalPages()) ? startPage + blockLimit - 1 : moonStatus.getTotalPages();

        model.addAttribute("moonsStatus",moonStatus);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("moonStatus", status); //현재 상태 1 2

        model.addAttribute("moons", adminMoonService.admin_paging(PageRequest.of(1, 7))); // 푸터용
        return "/adminad/admin_moon_status";
    }

}
