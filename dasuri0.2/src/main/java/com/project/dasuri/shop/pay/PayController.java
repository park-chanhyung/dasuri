package com.project.dasuri.shop.pay;

import com.project.dasuri.member.service.UserService;
import com.project.dasuri.shop.ShopService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shop")
public class PayController {

    private final UserService userService;
    private final PayService payService;
    private final ShopService shopService;

    @GetMapping("/payForm")
    public String payForm(Model model, Principal principal,
                          @RequestParam("price") String price,
                          @RequestParam("itemname") String itemname
                          ,PayForm payForm

    ) {
        String username = principal.getName();
        System.out.println("페이폼입니다##@!@" + username);
        model.addAttribute("username", username);
        model.addAttribute("price", price);
        model.addAttribute("itemname", itemname);
        System.out.println("price##@!@" + price);
        System.out.println("itemname##@!@" + itemname);
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
}
////    @PostMapping("/create")
////    public String questionCreate(@Valid QuestionForm questionForm,
////                                 BindingResult bindingResult,
////                                 Principal principal){
////        if(bindingResult.hasErrors()) {
////            return "question_form";
////        }
////        SiteUser siteUser = this.userService.getUser(principal.getName());
////        this.questionService.create(questionForm.getSubject(),questionForm.getContent(),siteUser);
////        return "redirect:/question/list";
////    }
//}
