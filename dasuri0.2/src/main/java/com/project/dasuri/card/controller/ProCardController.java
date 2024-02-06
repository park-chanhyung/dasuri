package com.project.dasuri.card.controller;

import com.project.dasuri.card.service.ProCardService;
import com.project.dasuri.member.dto.ProDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProCardController {

    private final ProCardService proCardService;

    //    기사프로필
//    @PostMapping("/prolist")
    @GetMapping("/prolist")
//    public String procardlist(@RequestParam("proId") String proId, Model model){
    public String procardlist(Model model) {
//        if (proId != null) {
//            // 만약 proId가 제공되었다면, 해당 proId를 사용하여 데이터베이스에서 특정 ProDTO를 가져올 수 있습니다.
//            ProDTO selectedPro = proCardService.findById(proId);
//            model.addAttribute("selectedPro", selectedPro);
//        }
        List<ProDTO> proDTOList = proCardService.findAll();
        model.addAttribute("cardList", proDTOList);
        // 이미지 경로를 모델에 추가
        for (ProDTO proDTO : proDTOList) {
            // 이미지 경로를 profileImagePath에 설정 (예시 경로, 실제로는 데이터베이스에서 가져온 값으로 설정해야 함)
//            proDTO.setProfileImagePath("/img/procard/" + proDTO.getProName() + ".jpg");
            proDTO.setProfileImagePath("/pro_files/" + proDTO.getFilename());

        }
//        ProDTO proDTO = proCardService.findById(proId);
//        model.addAttribute("pro", proDTO);
        return "/list/pro/prolist";
    }

}
