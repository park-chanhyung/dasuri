package com.project.dasuri;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
public class checkcontroller {
    @GetMapping("/index")
    public String login() {
//    public String index() {

        return "index";
    }

    @GetMapping("/notice")
    public String notice() {


        return "/list/notice";
    }

//    ------------------------------------------

    @GetMapping("/proinfo")
    public String proinfo() {
//    public String index() {

        return "/list/proinfo";
    }


    //    고객센터 메인 (자주찾는질문 / 문의하기로 갈라짐)
    @RequestMapping("/center_main")
    public String center_main() {

        return "/list/center_main";
    }
    //    메인 - 고객센터 - 자주찾는질문
    @RequestMapping("/center_faq")
    public String center_faq() {

        return "/list/center_faq";
    }
    //    메인 - 고객센터 - 1:1문의하기
    @RequestMapping("/center_question")
    public String center_question() {

        return "/list/center_question";
    }
    //    메인 - 고객센터 - 1:1문의 등록 완료
    @RequestMapping("/center_question_ok")
    public String center_question_ok() {

        return "/list/center_question_ok";
    }

    @RequestMapping("/boardwrite")
    public String boardwrite() {
//    public String index() {

        return "/list/boardwrite";
    }

}
