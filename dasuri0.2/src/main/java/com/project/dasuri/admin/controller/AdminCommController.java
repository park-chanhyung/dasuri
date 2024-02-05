package com.project.dasuri.admin.controller;

import com.project.dasuri.admin.dto.MoonDTO;
import com.project.dasuri.admin.service.Admin_CommService;
import com.project.dasuri.admin.service.Admin_MoonService;
import com.project.dasuri.community.dto.CommunityDto;
import com.project.dasuri.community.service.CommunityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class AdminCommController {
    private final CommunityService communityService;
    private final Admin_CommService adminCommService;
    private final Admin_MoonService adminMoonService;

    //  관리자페이지 > 커뮤니티
    @RequestMapping("/admin_community")
    public String admin_community(Model model) {
        List<CommunityDto> communityDtos = adminCommService.findAll();
        model.addAttribute("communityList",communityDtos);

        model.addAttribute("moons", adminMoonService.admin_paging(PageRequest.of(1, 7))); // 푸터용

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
        return "/adminad/admin_comm_search";
    }

}
