package com.project.dasuri.community.controller;

import com.project.dasuri.admin.service.Admin_UserService;
import com.project.dasuri.community.dto.CommentDto;
import com.project.dasuri.community.dto.CommunityDto;
import com.project.dasuri.community.service.CommentService;
import com.project.dasuri.community.service.CommunityService;
import com.project.dasuri.member.dto.UserDTO;
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

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
public class CommuController {
    private final CommunityService communityService;
    private final CommentService commentService;
    private final Admin_UserService adminUserService;
    //리스트 목록[페이징이랑 합침]
    @GetMapping("/Community_list")
    public String Communit_list(@PageableDefault(page = 1)Pageable pageable, Model model){

        pageable.getPageNumber();
        Page<CommunityDto> communityList = communityService.paging(pageable);
        int blockLimit = 5;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1; // 1 4 7 10 ~~
        int endPage = ((startPage + blockLimit - 1) < communityList.getTotalPages()) ? startPage + blockLimit - 1 : communityList.getTotalPages();

        model.addAttribute("communityList", communityList);
        model.addAttribute("startpage", startPage);
        model.addAttribute("endPage", endPage);

        return "/list/community/community_list";
    }


    //커뮤니티 게시판 검색
    @GetMapping("/search")
    public String searchCommunity(@RequestParam String community_keyword, @PageableDefault(page = 1) Pageable pageable, Model model){
//        일반공지 페이징 (고유번호 내림차순)
        Page<CommunityDto> communitySearchList = communityService.searchNo(pageable,community_keyword); //글번호, 제목, 수정일자

        int blockLimit  = 5;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) -1) * blockLimit + 1;
        int endPage = ((startPage + blockLimit - 1) < communitySearchList.getTotalPages()) ? startPage + blockLimit - 1 : communitySearchList.getTotalPages();

        model.addAttribute("searchc",communitySearchList); //검색페이지(페이징)
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        model.addAttribute("keyword",community_keyword);

        return "/list/community/community_searchResult";
    }

    //글작성
    @GetMapping("/community_post")
    public String community_post(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getPrincipal() == null || authentication.getPrincipal().equals("anonymousUser")) {
            // 사용자가 인증되지 않은 경우, 로그인 페이지로 redirect
            return "redirect:/login";
        }

        String id = SecurityContextHolder.getContext().getAuthentication().getName();

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
    public String post(@ModelAttribute CommunityDto communityDto, Model model) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String id = SecurityContextHolder.getContext().getAuthentication().getName();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority auth = iter.next();
        String role = auth.getAuthority();

        UserDTO userDTO = adminUserService.findByUserId(id);
        String Nickname =userDTO.getUserNickname();


        //사용자 아이디와 역할 가져오기
        communityDto.setUserID(id);
        communityDto.setRole(role);
        communityDto.setUserNickname(Nickname);

//        model.addAttribute("id", id);
//        model.addAttribute("role", role);

        communityService.save(communityDto);
        return "redirect:Community_list";
    }

    @GetMapping("Community_list/{id}")
    public String findById(@PathVariable Long id, Model model)throws IOException{

//        해당 게시글의 조회수를 하나 올리고
//        게시글 데이터를 가져와서 list/community/community_detail.html에출력

        communityService.updateHits(id);
        CommunityDto communityDto = communityService.findById(id);
//        댓글 목록 가져오기
        List<CommentDto> commentDtoList = commentService.findAll(id);

        model.addAttribute("commentList", commentDtoList);
        model.addAttribute("community", communityDto);
        return "list/community/community_detail";
    }

//    ID값을 받아와서 업데이트
    @GetMapping("/Update/{post_id}")
    public String updateForm(@PathVariable long post_id, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CommunityDto communityDto = communityService.findById(post_id);
        model.addAttribute("communityUpdate", communityDto);



        return "list/community/community_Update";

    }
    //    ID값을 받아와서 업데이트
    @PostMapping("/Update/")
    public String update(@ModelAttribute CommunityDto communityDto, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String id = SecurityContextHolder.getContext().getAuthentication().getName();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority auth = iter.next();
        String role = auth.getAuthority();

        UserDTO userDTO = adminUserService.findByUserId(id);
        String Nickname =userDTO.getUserNickname();


        //사용자 아이디와 역할 가져오기
        communityDto.setUserID(id);
        communityDto.setRole(role);
        communityDto.setUserNickname(Nickname);


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




}
