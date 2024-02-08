package com.project.dasuri.shop.controller;

import com.project.dasuri.member.entity.UserEntity;
import com.project.dasuri.member.service.UserService;
import com.project.dasuri.shop.entity.ShopEntity;
import com.project.dasuri.shop.form.ReviewForm;
import com.project.dasuri.shop.service.ReviewService;
import com.project.dasuri.shop.service.ShopService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {
    private final ShopService shopService;
    private final ReviewService reviewService;
    private final UserService userService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String createReview(Model model, @PathVariable("id") Long id, @Valid ReviewForm reviewForm,
                               BindingResult bindingResult, Principal principal) {
        ShopEntity shopEntity = this.shopService.getItem(id);
        UserEntity userEntity = this.userService.getUser(principal.getName());

        if (bindingResult.hasErrors()) {
            model.addAttribute("shopEntity", shopEntity);
            return "list/Shop/itemDetail";
        }
        this.reviewService.create(shopEntity, reviewForm.getComment(), userEntity, reviewForm.getReviewStar());

        return String.format("redirect:/shop/detail/%s", id);
    }


}