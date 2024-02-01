package com.project.dasuri;

import com.project.dasuri.admin.dto.FaqDTO;
import com.project.dasuri.admin.dto.NoticeDTO;
import com.project.dasuri.admin.service.FaqService;
import com.project.dasuri.admin.service.NoticeService;
import com.project.dasuri.card.service.ProCardService;
import com.project.dasuri.member.dto.ProDTO;
import com.project.dasuri.member.dto.UserDTO;
import com.project.dasuri.mypage.service.UserMyPageService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@AllArgsConstructor
@Controller
@Slf4j
public class checkcontroller {

    private final NoticeService noticeService; //공지사항 관련
    private final FaqService faqService; //자주찾는질문 관련
    private final ProCardService proCardService;
    private final UserMyPageService userMyPageService;

//    메인화면
    @GetMapping("/index")
    public String index(Model model) {

        System.out.println("@#@# 로그인 성공하면 메인페이지로 이동함.");

        //세션 현재 사용자 id값
        String id = SecurityContextHolder.getContext().getAuthentication().getName();

        //현재 로그인한 사용자의 role값
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority auth = iter.next();
        String role = auth.getAuthority();

        model.addAttribute("id", id);
        model.addAttribute("role",role);

        return "index";
    }
    
    //------------------------------- 공지사항 ------------------------------------------

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

//    메인 > 공지사항 > 공지 검색 (중요+일반)
    @RequestMapping("/notice_search")
    public String notice_search(@RequestParam String notice_keyword, Model model) {
        List<NoticeDTO> importantDTOs = noticeService.searchIm(notice_keyword); //중요공지 검색
        List<NoticeDTO> normalDTOs = noticeService.searchNo(notice_keyword); //일반공지 검색

        model.addAttribute("importants",importantDTOs);
        model.addAttribute("normals",normalDTOs);
        model.addAttribute("keyword",notice_keyword);

        return "/list/notice/notice_search_result";
    }
    //------------------------------- 전문가찾기 ------------------------------------------

//    메인 > 전문가찾기
    @GetMapping("/proinfo")
    public String proinfo(Model model) {
//    public String index() {
        List<ProDTO> proDTOList = proCardService.findAll();
        model.addAttribute("cardList", proDTOList);
        return "/list/pro/proinfo";
    }

    @GetMapping("/userprofile")
//    public String userprofile(@RequestParam("userId")String userId, Model model){
    public String userprofile(Model model){

        // Spring Security를 통해 로그인한 사용자의 정보를 가져옴
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 사용자 아이디 추출
        String userId = authentication.getName();
        UserDTO userDTO = userMyPageService.findById(userId);
        model.addAttribute("userpf", userDTO);
        return "mypage/userprofile";
    }



    //------------------------------- 고객센터 ------------------------------------------
    
    //    메인 > 고객센터 (자주찾는질문 / 문의하기로 갈라짐)
    @RequestMapping("/center_main")
    public String center_main() {

        return "/list/service/center_main";
    }

    //------------------------------- 자주찾는질문 ------------------------------------------

    //    메인 - 고객센터 - 자주찾는질문 (리스트)
    @RequestMapping("/center_faq")
    public String center_faq(Model model) {
        log.info("center_faq !!!!!!!!!!!!!!!!!!!!!!! ");
        List<FaqDTO> faqDTOS = faqService.findAll();
        model.addAttribute("faqs",faqDTOS);
        return "list/service/center_faq";
    }

    //    메인 - 고객센터 - 자주찾는질문 (해시태그 필터링)
    @RequestMapping("/center_faq_tag")
    public String center_faq_tag(@RequestParam String faqTag, Model model) {
        List<FaqDTO> faqDTOS = faqService.findByFaqTag(faqTag);
        model.addAttribute("faqs",faqDTOS);
        return "/list/service/center_faq_tag";
    }

    //    메인 - 고객센터 - 자주찾는질문 (검색)
    @RequestMapping("/center_faq_search")
    public String center_faq_search(@RequestParam String faq_keyword, Model model) {
        List<FaqDTO> faqDTOS = faqService.searchFaq(faq_keyword); //FAQ 검색

        model.addAttribute("faqs",faqDTOS);
        model.addAttribute("keyword",faq_keyword);

        return "/list/service/center_faq_search";
    }

    //------------------------------- 커뮤니티 ------------------------------------------

    @RequestMapping("/boardwrite")
    public String boardwrite() {
//    public String index() {

        return "/list/boardwrite";
    }

}
