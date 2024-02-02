package com.project.dasuri.member.controller;

import com.project.dasuri.member.dto.UserDTO;
import com.project.dasuri.member.service.ProService;
import com.project.dasuri.member.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/user_signup")
    public String user_signupP(UserDTO userDTO) {

        return "/login/user_signup";
    }

//        @PostMapping("/user_signup")
//    public String user_signup( @ModelAttribute UserDTO userDTO)
//    {
//        System.out.println("MemberController.sign_up");
//        System.out.println("userDTO = " + userDTO);
//        userService.user_signup(userDTO);
//
//        return "redirect:login";
//    }
    @PostMapping("/user_signup")
    public String user_signup(@Valid @ModelAttribute UserDTO userDTO, BindingResult br, Model model)
//    public String user_signup(@Valid UserDTO userDTO, BindingResult br , Model model)
    {
        System.out.println("MemberController.sign_up");
        System.out.println("userDTO = " + userDTO);
        model.addAttribute("userDTO", userDTO);
        if (br.hasErrors()) {
            //회원가입 실패시 기존 입력값 유지
            model.addAttribute("userDto", userDTO);
            return "/login/user_signup";
        } else {
            // 유효성 검사에 성공한 경우
            // 아이디 중복 확인
            boolean isDuplicate = userService.isUserIdDuplicate(userDTO.getUserId());
            boolean isNicknameDuplicate = userService.isUserNicknameDuplicate(userDTO.getUserNickname());
            if (isDuplicate||isNicknameDuplicate) {
                // 아이디가 중복된 경우
                model.addAttribute("userDTO", userDTO);
                model.addAttribute("errorMessage", "이미 사용 중인 아이디입니다.");
                model.addAttribute("NickerrorMessage","이미 사용 중인 닉네임입니다.");
                return "/login/user_signup";
            }else {
                // 아이디가 중복되지 않은 경우
                userService.user_signup(userDTO);
                return "redirect:login";
            }
        }
    }

    @PostMapping("/check_duplicate")
    @ResponseBody
    public ResponseEntity<?> checkDuplicateUserId(@RequestParam String userId) {
        boolean isDuplicate = userService.isUserIdDuplicate(userId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("duplicate", isDuplicate);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/check_duplicate_nickname")
    @ResponseBody
    public ResponseEntity<?> checkDuplicateUserNickname(@RequestParam String userNickname) {
        boolean isDuplicate = userService.isUserNicknameDuplicate(userNickname);
        System.out.println("유저 닉네임 중복검사중 = " + isDuplicate);
        Map<String, Boolean> response = new HashMap<>();
        response.put("duplicate", isDuplicate);
        return ResponseEntity.ok(response);
    }

}
