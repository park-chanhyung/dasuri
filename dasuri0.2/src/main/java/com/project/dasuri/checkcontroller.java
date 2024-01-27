package com.project.dasuri;

import com.project.dasuri.admin.dto.FaqDTO;
import com.project.dasuri.admin.dto.NoticeDTO;
import com.project.dasuri.admin.service.FaqService;
import com.project.dasuri.admin.service.NoticeService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@AllArgsConstructor
@Controller
public class checkcontroller {

    private final NoticeService noticeService; //공지사항 관련
    private final FaqService faqService; //자주찾는질문 관련


//    메인화면
    @GetMapping("/index")
    public String login() {
//    public String index() {

        return "index";
    }

//    메인 > 공지사항
    @GetMapping("/notice_main")
    public String notice(Model model) {
        List<NoticeDTO> importantDTOs = noticeService.findByImportantNotNull(); //중요공지리스트
        List<NoticeDTO> normalDTOs = noticeService.findByImportantNull(); //일반공지리스트
        model.addAttribute("importants",importantDTOs);
        model.addAttribute("normals",normalDTOs);

        return "/list/notice/notice_main";
    }

//    메인 > 공지사항 > 공지 보기
    @PostMapping("/notice_main_view")
    public String notice_main_view(@RequestParam Long id, Model model) {
        NoticeDTO noticeDTO = noticeService.findByNoticeId(id);
        if (noticeDTO.getImportant() == null) {
            noticeDTO.setNotice_type("일반");
        }else{
            noticeDTO.setNotice_type("중요");
        }
        model.addAttribute("notice",noticeDTO);

        return "/list/notice/notice_main_view";
    }

//    ------------------------------------------

//    메인 > 전문가찾기
    @GetMapping("/proinfo")
    public String proinfo() {
//    public String index() {

        return "/list/proinfo";
    }


    //    메인 > 고객센터 (자주찾는질문 / 문의하기로 갈라짐)
    @RequestMapping("/center_main")
    public String center_main() {

        return "/list/service/center_main";
    }

    //    메인 - 고객센터 - 자주찾는질문 (리스트)
    @RequestMapping("/center_faq")
    public String center_faq(Model model) {
        List<FaqDTO> faqDTOS = faqService.findAll();
        model.addAttribute("faqs",faqDTOS);
        return "/list/service/center_faq";
    }

    //    메인 - 고객센터 - 자주찾는질문 (해시태그 필터링)
    @RequestMapping("/center_faq_tag")
    public String center_faq_tag(@RequestParam String faqTag, Model model) {
        List<FaqDTO> faqDTOS = faqService.findByFaqTag(faqTag);
        model.addAttribute("faqs",faqDTOS);
        return "/list/service/center_faq_tag";
    }

    //    메인 - 고객센터 - 1:1문의하기
    @RequestMapping("/center_question")
    public String center_question() {

        return "/list/service/center_question";
    }
    //    메인 - 고객센터 - 1:1문의 등록 완료
    @RequestMapping("/center_question_ok")
    public String center_question_ok() {

        return "/list/service/center_question_ok";
    }




    @RequestMapping("/boardwrite")
    public String boardwrite() {
//    public String index() {

        return "/list/boardwrite";
    }

}
