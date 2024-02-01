package com.project.dasuri.community.controller;

import com.project.dasuri.community.dto.CommunityDto;
import com.project.dasuri.community.service.CommunityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
public class CommuController {
    private final CommunityService communityService;
    //리스트 목록
    @GetMapping("/Communit_list")
    public String Communit_list(Model model){
//        DB에서 전체 게시글 데이터를 가져와서 community_list.html에 보여준다
        List<CommunityDto> communityDtoList = communityService.findAll();
        log.info("!@communityDtoList== "+communityDtoList);
        model.addAttribute("commuList", communityDtoList);
        return "/list/community/community_list";
    }

    //글작성
    @GetMapping("/community_post")
    public String community_post(){
        return "list/community/community_post";
    }

    //글작성 저장
    @PostMapping("/community_post")
    public String post(@ModelAttribute CommunityDto communityDto){
//        System.out.println("communityDto==> "+communityDto );
        communityService.save(communityDto);

        return "redirect:Communit_list";
    }
}
