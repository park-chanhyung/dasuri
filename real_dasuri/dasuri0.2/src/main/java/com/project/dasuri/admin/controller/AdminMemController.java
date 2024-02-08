package com.project.dasuri.admin.controller;

import com.project.dasuri.admin.dto.MoonDTO;
import com.project.dasuri.admin.service.*;
import com.project.dasuri.member.dto.ProDTO;
import com.project.dasuri.member.dto.UserDTO;
import com.project.dasuri.member.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminMemController {
    private final Admin_UserService userService;
    private final Admin_ProService proService;
    private final Admin_MoonService adminMoonService;
    private final Admin_LocService adminLocService;
    private final UserService k_userService;
    private final Admin_ReportService adminReportService;

    //  관리자 페이지 > 회원관리 (회원리스트 > 고객리스트 기본) - 페이징
    @RequestMapping("/admin_mem")
    public String admin_mem(@PageableDefault(page = 1) Pageable pageable, Model model) {
        // 고객리스트 페이징 (고유번호 내림차순)
        // 아이디,이름,닉네임,주소,유형,가입일,정지여부
        Page<UserDTO> userDTOS = userService.admin_paging(pageable);

        int blockLimit = 5;
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = ((startPage + blockLimit - 1) < userDTOS.getTotalPages()) ? startPage + blockLimit - 1 : userDTOS.getTotalPages();

        model.addAttribute("users",userDTOS);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        model.addAttribute("moons", adminMoonService.admin_paging(PageRequest.of(1, 7))); // 푸터용
        model.addAttribute("report", adminReportService.todayReport()); // 푸터용2
        return "/adminad/admin_mem_user";
    }

    //  관리자 페이지 > 회원관리 (회원리스트 > 기사리스트) - 페이징
    @RequestMapping("/admin_mem_pro")
    public String admin_mem_pro(@PageableDefault(page = 1) Pageable pageable, Model model) {
        // 기사리스트 페이징 (고유번호 내림차순)
        // 아이디,이름,업체명,활동지역,가입일,정지여부
        Page<ProDTO> proDTOS = proService.admin_paging(pageable);

        int blockLimit = 5;
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = ((startPage + blockLimit - 1) < proDTOS.getTotalPages()) ? startPage + blockLimit - 1 : proDTOS.getTotalPages();

        model.addAttribute("pros",proDTOS);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        model.addAttribute("moons", adminMoonService.admin_paging(PageRequest.of(1, 7))); // 푸터용
        model.addAttribute("report", adminReportService.todayReport()); // 푸터용2
        return "/adminad/admin_mem_pro";
    }

    //  관리자 페이지 > 회원관리 (회원리스트 > 회원보기)
    @PostMapping("/admin_mem_user_view")
    public String admin_mem_user_view(@RequestParam String userId, Model model) {
        UserDTO userDTO = userService.findByUserId(userId);
        model.addAttribute("user",userDTO);

        model.addAttribute("moons", adminMoonService.admin_paging(PageRequest.of(1, 7))); // 푸터용
        model.addAttribute("report", adminReportService.todayReport()); // 푸터용2
        return "/adminad/admin_mem_user_view";
    }

    //  관리자 페이지 > 회원관리 (회원리스트 > 기사보기)
    @PostMapping("/admin_mem_pro_view")
    public String admin_mem_pro_view(@RequestParam String proId, Model model) {
        ProDTO proDTO = proService.findByProId(proId);
        model.addAttribute("pro",proDTO);

        model.addAttribute("moons", adminMoonService.admin_paging(PageRequest.of(1, 7))); // 푸터용
        model.addAttribute("report", adminReportService.todayReport()); // 푸터용2
        return "/adminad/admin_mem_pro_view";
    }

    //  관리자 페이지 > 회원관리 (회원리스트 > 회원보기 > 정지)
    @RequestMapping("/admin_suspendUser")
    public String admin_suspendUser(@RequestParam("userId") String userId, @RequestParam("duration") int duration, Model model) {
        LocalDateTime day = LocalDateTime.now().plusDays(duration);
        k_userService.suspendUser(userId,day); // 선택 일수만큼 정지
        return "/adminad/admin_suspendMem_ok";
    }

    //  관리자 페이지 > 회원관리 (회원리스트 > 회원보기 > 정지 해제)
    @RequestMapping("/admin_resumeUser")
    public String admin_resumeUser(@RequestParam("userId") String userId, Model model) {
        LocalDateTime day = null;
        k_userService.suspendUser(userId,day); // 정지일을 null로 변경 (정지 해제)
        return "/adminad/admin_resumeMem_ok";
    }

//    관리자 페이지 > 회원관리 (고객 검색)
    @RequestMapping("/admin_user_search")
    public String admin_user_search(@RequestParam String keyword, @PageableDefault(page = 1) Pageable pageable, Model model) {

//        고객 검색결과
        Page<UserDTO> userDTOS = userService.userSearch(keyword, pageable);

        int blockLimit = 5;
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = ((startPage + blockLimit - 1) < userDTOS.getTotalPages()) ? startPage + blockLimit - 1 : userDTOS.getTotalPages();

        model.addAttribute("users",userDTOS);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("keyword", keyword);

        model.addAttribute("moons", adminMoonService.admin_paging(PageRequest.of(1, 7))); // 푸터용
        model.addAttribute("report", adminReportService.todayReport()); // 푸터용2
        return "/adminad/admin_mem_user_search";
    }

//    관리자 페이지 > 회원관리 (기사 검색)
    @RequestMapping("/admin_pro_search")
    public String admin_pro_search(@RequestParam String keyword, @PageableDefault(page = 1) Pageable pageable, Model model) {

//        기사 검색결과
        Page<ProDTO> proDTOS = proService.proSearch(keyword, pageable);

        int blockLimit = 5;
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = ((startPage + blockLimit - 1) < proDTOS.getTotalPages()) ? startPage + blockLimit - 1 : proDTOS.getTotalPages();

        model.addAttribute("pros",proDTOS);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("keyword", keyword);

        model.addAttribute("moons", adminMoonService.admin_paging(PageRequest.of(1, 7))); // 푸터용
        model.addAttribute("report", adminReportService.todayReport()); // 푸터용2
        return "/adminad/admin_mem_pro_search";
    }

//    메인 > 전문가찾기 > 지역별 기사리스트
//    @RequestMapping("/loc_pro")
//    public String loc_pro(@RequestParam String loc, Model model) {
//        List<ProDTO> proDTOS = adminLocService.locPro(loc);
//        model.addAttribute("cardList",proDTOS);
//
//        return "/list/pro/proinfo_loc";
//    }

}
