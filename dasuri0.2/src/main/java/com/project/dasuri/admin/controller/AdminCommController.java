package com.project.dasuri.admin.controller;

import com.project.dasuri.admin.dto.MoonDTO;
import com.project.dasuri.admin.service.Admin_CommService;
import com.project.dasuri.admin.service.Admin_MoonService;
import com.project.dasuri.admin.service.Admin_ReportService;
import com.project.dasuri.community.dto.CommunityDto;
import com.project.dasuri.community.service.CommunityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RequiredArgsConstructor
public class AdminCommController {
    private final CommunityService communityService;
    private final Admin_CommService adminCommService;
    private final Admin_MoonService adminMoonService;
    private final Admin_ReportService adminReportService;

    //  관리자페이지 > 커뮤니티 (페이징)
    @RequestMapping("/admin_community")
    public String admin_community(@PageableDefault(page = 1) Pageable pageable, Model model) {

        // 문의글 페이징 (고유번호 내림차순)
        // admindeleted,번호,role,아이디,닉네임,제목, 조회수
        Page<CommunityDto> communityDtos = adminCommService.admin_paging(pageable);

        // 현재 페이지에서 앞 뒤 갯수
        int blockLimit = 5;
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = ((startPage + blockLimit - 1) < communityDtos.getTotalPages()) ? startPage + blockLimit - 1 : communityDtos.getTotalPages();

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("communityList",communityDtos);

        model.addAttribute("moons", adminMoonService.admin_paging(PageRequest.of(1, 7))); // 푸터용
        model.addAttribute("report", adminReportService.todayReport()); // 푸터용2
        return "/adminad/admin_community";
    }

//    관리자페이지 > 커뮤니티 > 메인의 게시물 조회로
    @PostMapping("/commView")
    public String findById(@PathVariable Long post_id, Model model){
//        해당 게시글의 조회수를 하나 올리고
//        게시글 데이터를 가져와서 list/community/community_detail.html에출력

        communityService.updateHits(post_id);
        CommunityDto communityDto = communityService.findById(post_id);
        model.addAttribute("community", communityDto);
        return "list/community/community_detail";
    }

//    관리자페이지 > 커뮤니티 > 게시물 검색
    @RequestMapping("/admin_comm_search")
    public String admin_comm_search(@RequestParam String comm_keyword, Model model) {
        List<CommunityDto> communityDtos = adminCommService.searchComm(comm_keyword);

        model.addAttribute("communityList",communityDtos); //게시물 검색결과
        model.addAttribute("keyword",comm_keyword); //키워드

        model.addAttribute("moons", adminMoonService.admin_paging(PageRequest.of(1, 7))); // 푸터용
        model.addAttribute("report", adminReportService.todayReport()); // 푸터용2
        return "/adminad/admin_comm_search";
    }

//    게시물 블라인드 처리 (게시판에서 안보이고, 관리자페이지에만 보임)
    @GetMapping("/admin_comm_blind/{id}")
    public String admin_comm_blind(@PathVariable Long id) throws IOException {
        CommunityDto communityDto = communityService.findById(id);
        communityDto.setAdminDeleted("y"); //관리자블라인드 y 처리
        communityService.save(communityDto); //다시 저장

        return "redirect:/board/Community_list"; //커뮤니티로 돌아감
    }

}
