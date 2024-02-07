package com.project.dasuri.community.controller;

import com.project.dasuri.admin.service.Admin_UserService;
import com.project.dasuri.community.dto.CommentDto;
import com.project.dasuri.community.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private  final CommentService commentService;
    private final Admin_UserService adminUserService;
    @PostMapping("/save")
//    public @ResponseBody String save(@ModelAttribute CommentDto commentDto){
    public ResponseEntity  save(@ModelAttribute CommentDto commentDto){
        System.out.println("commentDto: "+commentDto);

//        String id = SecurityContextHolder.getContext().getAuthentication().getName();
//
//        UserDTO userDTO = adminUserService.findByUserId(id);
//        String Nickname =userDTO.getUserNickname();
//        CommentDto.
//        String userId = null;
//
//        // principal이 null이 아닌지 확인 후 속성에 액세스
//        if (principal != null) {
//            userId = principal.getName();
//            commentDto.setUserId(userId);
//            UserDTO userDTO = adminUserService.findByUserId(userId);
//            String Nickname =userDTO.getUserNickname();
//            commentDto.setUserNickname(Nickname);
//        }

        Long saveResult = commentService.save(commentDto);
        if (saveResult != null){
//            작성 성공하면 댓글목록을 가져와서 리턴
//            댓글목록: 해당 게시글의 댓글 전체
            List<CommentDto> commentDtoList = commentService.findAll(commentDto.getBoardId());
            return new ResponseEntity<>(commentDtoList, HttpStatus.OK);
        } else{
             return new ResponseEntity<>("해당 게시글이 존재하지 않습니다.", HttpStatus.NOT_FOUND) ;
        }
    }
}
