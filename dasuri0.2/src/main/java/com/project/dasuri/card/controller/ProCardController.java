package com.project.dasuri.card.controller;

import com.project.dasuri.admin.dto.MoonDTO;
import com.project.dasuri.admin.service.Admin_LocService;
import com.project.dasuri.card.service.ProCardService;
import com.project.dasuri.member.dto.ProDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProCardController {

    private final ProCardService proCardService;
    private final Admin_LocService adminLocService;

    //    기사프로필
//    @PostMapping("/prolist")
//    @GetMapping("/prolist")
    @GetMapping("/prolist")
//    public String procardlist(@RequestParam("proId") String proId, Model model){
//    public String procardlist(@RequestParam String loc,@PageableDefault(page = 1) Pageable pageable,Model model) {
    public String procardlist(@PageableDefault(page = 1) Pageable pageable,Model model) {
//        public String procardlist(Model model) {
//        if (proId != null) {
//            // 만약 proId가 제공되었다면, 해당 proId를 사용하여 데이터베이스에서 특정 ProDTO를 가져올 수 있습니다.
//            ProDTO selectedPro = proCardService.findById(proId);
//            model.addAttribute("selectedPro", selectedPro);
//        }
        // Spring Security를 통해 로그인한 사용자의 정보를 가져옴
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 사용자 아이디 추출
        String proId = authentication.getName();

//        List<ProDTO> locPro = adminLocService.locPro(loc);
//        model.addAttribute("locList",locPro);

        Page<ProDTO> proDTOS = proCardService.pro_paging(proId,pageable);

        // 현재 페이지에서 앞 뒤 갯수
        int blockLimit = 5;
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = ((startPage + blockLimit - 1) < proDTOS.getTotalPages()) ? startPage + blockLimit - 1 : proDTOS.getTotalPages();
//        List<ProDTO> proDTOList = proCardService.findAll();

        List<ProDTO> proDTOList = proCardService.findAll();
        model.addAttribute("procardList", proDTOList);
        model.addAttribute("cardList", proDTOS);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        // 이미지 경로를 모델에 추가
        for (ProDTO proDTO : proDTOS) {
            // 이미지 경로를 profileImagePath에 설정 (예시 경로, 실제로는 데이터베이스에서 가져온 값으로 설정해야 함)
//            proDTO.setProfileImagePath("/img/procard/" + proDTO.getProName() + ".jpg");
            proDTO.setProfileImagePath("/pro_files/" + proDTO.getFilename());

        }

//        ProDTO proDTO = proCardService.findById(proId);
//        model.addAttribute("pro", proDTO);
        return "list/pro/prolist";
    }
//    @RequestMapping("/loc_pro")
//    public String loc_pro(@RequestParam String loc, Model model) {
//        List<ProDTO> proDTOS = adminLocService.locPro(loc);
//        model.addAttribute("locList",proDTOS);
//
//        return "/list/pro/proinfo_loc :: procard-list";
//    }
}
