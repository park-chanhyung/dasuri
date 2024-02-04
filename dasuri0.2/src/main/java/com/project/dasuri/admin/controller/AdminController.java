package com.project.dasuri.admin.controller;//package com.project.dasuri.controller;

import com.project.dasuri.admin.dto.LocCount;
import com.project.dasuri.admin.dto.MoonDTO;
import com.project.dasuri.admin.service.Admin_LocService;
import com.project.dasuri.admin.service.Admin_MoonService;
import com.project.dasuri.admin.service.Admin_ProService;
import com.project.dasuri.admin.service.Admin_UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AdminController {
    private final Admin_MoonService adminMoonService;
    private final Admin_ProService proService;
    private final Admin_UserService userService;
    private final Admin_LocService adminLocService;

//    관리자 페이지 (메인)
    @RequestMapping("/admin")
    public String admin_home(Model model) {

//        -------- 전체 회원 수 카운트 ---------
        Long proCount = proService.proCount(); //기사 수
        Long userCount = userService.userCount(); //고객 수
        Long totalUser = proCount + userCount; // 총 회원 수
        model.addAttribute("totalUser",totalUser);
        model.addAttribute("userCount",userCount);
        model.addAttribute("proCount",proCount);

//        -------- 날짜별 신규가입 수 카운트 ---------
        List<Long> counts_user = userService.userSignupDateCount(); //오늘부터 6일전까지 각각 날짜에 가입한 고객 수
        List<Long> counts_pro = proService.userSignupDateCount(); //오늘부터 6일전까지 각각 날짜에 가입한 기사 수
        for (int i = 0; i < counts_user.size(); i++) {
            Long total = counts_user.get(i) + counts_pro.get(i);
            model.addAttribute("day" + i, total); //day0:오늘 , day1:1일 전 ..
        }

//        -------- 지역별 회원 수 top3 ---------
        List<LocCount> locCounts = adminLocService.top3Users();

        for (int i = 0; i < 3; i++) {
            model.addAttribute("userLocName"+i, locCounts.get(i).getLoc());
            model.addAttribute("userLocAmount"+i, locCounts.get(i).getAmount());
        }


//        푸터용
        List<MoonDTO> moonDTOS = adminMoonService.findAll();
        model.addAttribute("moons",moonDTOS);
        return "/adminad/admin_home";
    }

}
