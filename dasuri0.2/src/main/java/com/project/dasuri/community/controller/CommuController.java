package com.project.dasuri.community.controller;

import com.project.dasuri.community.dto.CommunityDto;
import com.project.dasuri.community.service.CommunityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Iterator;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
public class CommuController {
    private final CommunityService communityService;
    //리스트 목록[페이징이랑 합침]
    @GetMapping("/Community_list")
    public String Communit_list(@PageableDefault(page = 1)Pageable pageable, Model model){
//        DB에서 전체 게시글 데이터를 가져와서 community_list.html에 보여준다
//        List<CommunityDto> communityDtoList = communityService.findAll();
//        model.addAttribute("commuList", communityDtoList);

        //    public String Communit_list(Model model){
////        DB에서 전체 게시글 데이터를 가져와서 community_list.html에 보여준다
//        List<CommunityDto> communityDtoList = communityService.findAll();
//        model.addAttribute("commuList", communityDtoList);
//        return "/list/community/community_list";
//    }

        pageable.getPageNumber();
        Page<CommunityDto> communityList = communityService.paging(pageable);
        int blockLimit = 3;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1; // 1 4 7 10 ~~
        int endPage = ((startPage + blockLimit - 1) < communityList.getTotalPages()) ? startPage + blockLimit - 1 : communityList.getTotalPages();

        model.addAttribute("communityList", communityList);
        model.addAttribute("startpage", startPage);
        model.addAttribute("endPage", endPage);

        return "/list/community/community_list";
    }

    //글작성
    @GetMapping("/community_post")
    public String community_post(Model model){

        String id = SecurityContextHolder.getContext().getAuthentication().getName();


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority auth = iter.next();
        String role = auth.getAuthority();

        model.addAttribute("id", id);
        model.addAttribute("role", role);

        System.out.println(id);
        System.out.println(role);

        return "list/community/community_post";
    }

    //글작성 저장
    @PostMapping("/community_post")
    public String post(@ModelAttribute CommunityDto communityDto, Model model){

        String id = SecurityContextHolder.getContext().getAuthentication().getName();


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority auth = iter.next();
        String role = auth.getAuthority();

        communityDto.setUserID(id);
        communityDto.setRole(role);

        model.addAttribute("id", id);
        model.addAttribute("role", role);

        communityService.save(communityDto);
        return "redirect:Community_list";
    }

    @GetMapping("Community_list/{post_id}")
    public String findById(@PathVariable Long post_id, Model model){
//        해당 게시글의 조회수를 하나 올리고
//        게시글 데이터를 가져와서 list/community/community_detail.html에출력

        communityService.updateHits(post_id);
        CommunityDto communityDto = communityService.findById(post_id);
        model.addAttribute("community", communityDto);
        return "list/community/community_detail";
    }

//    ID값을 받아와서 업데이트
    @GetMapping("/Update/{post_id}")
    public String updateForm(@PathVariable long post_id, Model model){
        CommunityDto communityDto = communityService.findById(post_id);
        model.addAttribute("communityUpdate", communityDto);

        return "list/community/community_Update";

    }
    //    ID값을 받아와서 업데이트
    @PostMapping("/Update/")
    public String update(@ModelAttribute CommunityDto communityDto, Model model){
        CommunityDto community =  communityService.update(communityDto);
        model.addAttribute("community", community);
        return "/list/community/community_detail";
    }

//    삭제
    @GetMapping("/delete/{post_id}")
        public String delete(@PathVariable Long post_id){
            communityService.delete(post_id);
            return "redirect:/board/Community_list";
    }
//페이징 리스트랑 합치기전
//    /board/paging?page=1
//    @GetMapping("/paging")
//    public String paging(@PageableDefault(page = 1)Pageable pageable, Model model){
//        pageable.getPageNumber();
//        Page<CommunityDto> communityList = communityService.paging(pageable);
//        int blockLimit = 3;
//        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1; // 1 4 7 10 ~~
//        int endPage = ((startPage + blockLimit - 1) < communityList.getTotalPages()) ? startPage + blockLimit - 1 : communityList.getTotalPages();
//
//        model.addAttribute("communityList", communityList);
//        model.addAttribute("startpage", startPage);
//        model.addAttribute("endPage");
//        return "paging";
//    }
}
