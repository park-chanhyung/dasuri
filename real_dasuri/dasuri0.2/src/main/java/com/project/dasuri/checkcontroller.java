package com.project.dasuri;

import com.project.dasuri.admin.dto.FaqDTO;
import com.project.dasuri.admin.dto.NoticeDTO;
import com.project.dasuri.admin.entity.NoticeEntity;
import com.project.dasuri.admin.repository.NoticeRepository;
import com.project.dasuri.admin.service.FaqService;
import com.project.dasuri.admin.service.NoticeService;
import com.project.dasuri.card.service.ProCardService;
import com.project.dasuri.chat.mysql.MysqlChatService;
import com.project.dasuri.community.dto.CommunityDto;
import com.project.dasuri.member.dto.ProDTO;
import com.project.dasuri.member.dto.UserDTO;
import com.project.dasuri.member.entity.UserEntity;
import com.project.dasuri.member.service.UserService;
import com.project.dasuri.mypage.service.UserMyPageService;
import com.project.dasuri.shop.entity.ShopEntity;
import com.project.dasuri.shop.service.ShopService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
    private final UserService userService;
    private final MysqlChatService mysqlChatService;
    private final ShopService shopService;
//    메인화면
@GetMapping("/index")
public String index(Model model, Principal principal) {

    System.out.println("@#@# 로그인 성공하면 메인페이지로 이동함.");

    if (principal != null) {
        String userId = principal.getName();
        System.out.println("userID 아이디!!@#!@#" + userId);

        UserEntity user = userService.mappingId(userId);
        model.addAttribute("id", userId); // 현재 사용자 아이디
        model.addAttribute("user", user);
        System.out.println("user 아이디!!@#!@#" + user);

        Integer maxroomNum = mysqlChatService.findmaxRoomNum();
        if (maxroomNum != null) {
            model.addAttribute("maxroomNum", maxroomNum);
        } else {
            return "/index"; // 로그인 페이지의 URL
        }
    }

    //현재 로그인한 사용자의 role값
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
    Iterator<? extends GrantedAuthority> iter = authorities.iterator();
    GrantedAuthority auth = iter.next();
    String role = auth.getAuthority();

    model.addAttribute("role",role);

    // 로그인 여부와 상관 없이 bestItem 값을 가져옵니다.
    ShopEntity bestItem = shopService.findProductWithMostReviews();
    model.addAttribute("bestitem", bestItem);
    System.out.println("베스트아이템"+bestItem);

    return "index";
}
    
    //------------------------------- 공지사항 ------------------------------------------

    //    메인 > 공지사항 (페이징)
    @GetMapping("/notice_main")
    public String notice(@PageableDefault(page = 1) Pageable pageable,  Model model) {
//        일반공지 페이징 (고유번호 내림차순)
        Page<NoticeDTO> normalDTOs = noticeService.paging(pageable); //글번호, 제목, 수정일자

//        현재 페이지에서 앞 뒤 갯수 ex> 1 2 3 (4) 5 6 7
        int blockLimit  = 5;

        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) -1) * blockLimit + 1;
        int endPage = ((startPage + blockLimit - 1) < normalDTOs.getTotalPages()) ? startPage + blockLimit - 1 : normalDTOs.getTotalPages();

        model.addAttribute("normals",normalDTOs); //일반공지리스트(페이징)
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);

        List<NoticeDTO> importantDTOs = noticeService.findByImportantNotNull();
        model.addAttribute("importants",importantDTOs); //중요공지리스트

        return "/list/notice/notice_main";
    }

//    메인 > 공지사항 > 공지 보기
    @GetMapping("/notice_main_view/{id}")
    public String notice_main_view(@PathVariable Long id, Model model) {
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
    @GetMapping("/notice_search")
    public String notice_search(@RequestParam String notice_keyword, @PageableDefault(page = 1) Pageable pageable, Model model) {
//        일반공지 페이징 (고유번호 내림차순)
        Page<NoticeDTO> normalDTOs = noticeService.searchNo(pageable,notice_keyword); //글번호, 제목, 수정일자

        int blockLimit  = 5;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) -1) * blockLimit + 1;
        int endPage = ((startPage + blockLimit - 1) < normalDTOs.getTotalPages()) ? startPage + blockLimit - 1 : normalDTOs.getTotalPages();

        model.addAttribute("normals",normalDTOs); //일반공지리스트(페이징)
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);

        List<NoticeDTO> importantDTOs = noticeService.searchIm(notice_keyword); //중요공지 검색

        model.addAttribute("importants",importantDTOs);
        model.addAttribute("keyword",notice_keyword);

        return "/list/notice/notice_search_result";
    }
    //------------------------------- 전문가찾기 ------------------------------------------

//    메인 > 전문가찾기
//    @GetMapping("/proinfo")
//    public String proinfo(Model model) {
////    public String index() {
//        List<ProDTO> proDTOList = proCardService.findAll();
//        model.addAttribute("cardList", proDTOList);
//        return "/list/pro/proinfo";
//    }

//    @GetMapping("/userprofile")
////    public String userprofile(@RequestParam("userId")String userId, Model model){
//    public String userprofile(Model model){
//
//        // Spring Security를 통해 로그인한 사용자의 정보를 가져옴
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        // 사용자 아이디 추출
//        String userId = authentication.getName();
//        UserDTO userDTO = userMyPageService.findById(userId);
//        model.addAttribute("userpf", userDTO);
//        return "usermypage/userprofile";
//    }

    //------------------------------- 고객센터 ------------------------------------------
    
    //    메인 > 고객센터 (자주찾는질문 / 문의하기로 갈라짐)
    @RequestMapping("/center_main")
    public String center_main() {

        return "/list/service/center_main";
    }

    //------------------------------- 자주찾는질문 ------------------------------------------

    //    메인 - 고객센터 - 자주찾는질문 (리스트)
    @RequestMapping("/center_faq")
    public String center_faq(@PageableDefault(page = 1) Pageable pageable, Model model) {
//        faq 리스트 (faqId 내림차순)
        Page<FaqDTO> faqDTOS = faqService.admin_paging(pageable);

        // 현재 페이지에서 앞 뒤 갯수
        int blockLimit = 5;
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = ((startPage + blockLimit - 1) < faqDTOS.getTotalPages()) ? startPage + blockLimit - 1 : faqDTOS.getTotalPages();

        model.addAttribute("faqs",faqDTOS);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "list/service/center_faq";
    }

    //    메인 - 고객센터 - 자주찾는질문 (해시태그 필터링)
    @RequestMapping("/center_faq_tag")
    public String center_faq_tag(@RequestParam String faqTag,@PageableDefault(page = 1) Pageable pageable, Model model) {
        Page<FaqDTO> faqDTOS = faqService.findByFaqTag(pageable, faqTag);

        // 현재 페이지에서 앞 뒤 갯수
        int blockLimit = 5;
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = ((startPage + blockLimit - 1) < faqDTOS.getTotalPages()) ? startPage + blockLimit - 1 : faqDTOS.getTotalPages();

        model.addAttribute("faqs",faqDTOS);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("faqTag", faqTag);

        return "/list/service/center_faq_tag";
    }

    //    메인 - 고객센터 - 자주찾는질문 (검색)
    @RequestMapping("/center_faq_search")
    public String center_faq_search(@RequestParam String faq_keyword, @PageableDefault(page = 1) Pageable pageable, Model model) {
        // faq 페이징 (고유번호 내림차순)
        Page<FaqDTO> faqDTOS = faqService.searchFaq(faq_keyword, pageable); //faq 검색결과

        // 현재 페이지에서 앞 뒤 갯수
        int blockLimit = 5;
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = ((startPage + blockLimit - 1) < faqDTOS.getTotalPages()) ? startPage + blockLimit - 1 : faqDTOS.getTotalPages();


        model.addAttribute("faqs",faqDTOS);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("faq_keyword",faq_keyword);

        return "/list/service/center_faq_search";
    }

    //------------------------------- 커뮤니티 ------------------------------------------

    @RequestMapping("/boardwrite")
    public String boardwrite() {
//    public String index() {

        return "/list/boardwrite";
    }

}
