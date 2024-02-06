package com.project.dasuri.shop.controller;

import com.project.dasuri.member.service.UserService;
import com.project.dasuri.shop.entity.PayEntity;
import com.project.dasuri.shop.entity.ShopEntity;
import com.project.dasuri.shop.form.PayForm;
import com.project.dasuri.shop.service.PayService;
import com.project.dasuri.shop.service.ReviewService;
import com.project.dasuri.shop.service.ShopService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shop")
public class PayController {

    private final UserService userService;
    private final PayService payService;
    private final ShopService shopService;
    private final ReviewService reviewService;

    @GetMapping("/payForm/{id}")
    public String payForm(Model model, Principal principal,
                          @RequestParam("price") String price,
                          @RequestParam("itemname") String itemname
                          , PayForm payForm,@PathVariable("id") Long id

    ) {
        String username = principal.getName();
        System.out.println("페이폼입니다##@!@" + username);
        model.addAttribute("username", username);
        model.addAttribute("price", price);
        model.addAttribute("itemname", itemname);
        System.out.println("price##@!@" + price);
        System.out.println("itemname##@!@" + itemname);
        ShopEntity shopEntity = this.shopService.getItem(id);
        model.addAttribute("item", shopEntity);
        Double avgRating = this.reviewService.calculateAverageRating(shopEntity);
        model.addAttribute("avgRating", avgRating);
        return "list/Shop/payForm";
    }

    @PostMapping("/payForm")
    public String savePay(@Valid PayForm payForm, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "list/Shop/payForm";
        }

        this.payService.savePay(payForm);
        System.out.println("payForm!@#!@"+payForm);
        return "redirect:/shop";  // 성공적으로 저장 후에 리다이렉트할 페이지를 지정하세요.
    }
    @GetMapping("/payments")
    public String findpayments(Principal principal,Model model){
        String userId =principal.getName();
        List<PayEntity> payCheck = payService.findByUserIdPayCheck(userId);
        model.addAttribute("payCheck",payCheck);
        System.out.println("페이체크#!@#!#!@"+payCheck);
        return "list/Shop/payments";
    }

}

