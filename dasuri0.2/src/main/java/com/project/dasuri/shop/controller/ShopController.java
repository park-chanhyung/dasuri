package com.project.dasuri.shop.controller;

import com.project.dasuri.shop.entity.ShopEntity;
import com.project.dasuri.shop.form.ReviewForm;
import com.project.dasuri.shop.form.ShopForm;
import com.project.dasuri.shop.service.ReviewService;
import com.project.dasuri.shop.service.ShopService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
//@RequestMapping("/shop")
public class ShopController {
    private final ShopService shopService;
    private final ReviewService reviewService;

    @GetMapping("/shop")
    public String shop(Model model, @RequestParam(value = "page",defaultValue = "0")int page) {
        Page<ShopEntity> paging = shopService.itemlist(page);

        for (ShopEntity shopEntity : paging.getContent()) {
            Double avgStar = reviewService.starAvg(shopEntity);
            if (avgStar != null) {
                shopEntity.setAvgStar(avgStar);
            } else {
                shopEntity.setAvgStar(0.0); // 평점이 없을 경우 0으로 처리하거나 다른 방법으로 처리할 수 있습니다.
            }
        }
        model.addAttribute("paging", paging);
        return "list/Shop/shop";
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/shop/create")
    public String createForm(ShopForm shopForm){

        return  "list/Shop/shopForm";
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/shop/create")
    public String itemCreate(@Valid ShopForm shopForm, BindingResult bindingResult,  @RequestParam("file")MultipartFile file) throws IOException {
        if(bindingResult.hasErrors()){
            return "list/Shop/shopForm";
        }
        this.shopService.create(shopForm.getId(),shopForm.getItemname(),
                shopForm.getPrice(),shopForm.getSeller(),
                shopForm.getDeliveryinfo(),shopForm.getShortinfo(),shopForm.getIteminfo(),file);

        return "redirect:/shop";
    }

    @GetMapping(value = "/shop/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model, ReviewForm reviewForm){
        ShopEntity shopEntity = this.shopService.getItem(id); // 변수 이름을 shopEntity로 변경
        // 리뷰 평균 별점을 계산하여 모델에 추가
        Double avgRating = this.reviewService.calculateAverageRating(shopEntity);
        model.addAttribute("avgRating", avgRating);
        model.addAttribute("item", shopEntity);
        return "list/Shop/itemDetail";
    }
    @GetMapping("/shop/modify/{id}")
    public String itemModify(ShopForm shopForm, @PathVariable("id") Long id) {
        ShopEntity item = this.shopService.getItem(id);

        shopForm.setItemname(item.getItemname());
        shopForm.setPrice(item.getPrice());
        shopForm.setSeller(item.getSeller());
        shopForm.setDeliveryinfo(item.getDeliveryinfo());
        shopForm.setFile(shopForm.getFile());
        shopForm.setShortinfo(shopForm.getShortinfo());
        shopForm.setIteminfo(shopForm.getIteminfo());
        return "list/Shop/shopForm";
    }
    @PostMapping("/shop/modify/{id}")
    public String itemModify(@Valid ShopForm shopForm, BindingResult bindingResult, @PathVariable("id")Long id){
        if (bindingResult.hasErrors()) {
            System.out.println("hasError");
            return "list/Shop/shopForm";
        }
        ShopEntity item = this.shopService.getItem(id);
        try {
            this.shopService.modify(item, shopForm.getItemname(), shopForm.getPrice(),
                    shopForm.getSeller(), shopForm.getDeliveryinfo(),shopForm.getShortinfo(),
                    shopForm.getIteminfo(),shopForm.getFile());
        } catch (IOException e) {
            e.printStackTrace();
            return "list/Shop/shopForm";
        }
        System.out.println("리턴 리다이렉트 ");
        return String.format("redirect:/shop/detail/%s",id);
    }

    @GetMapping("/shop/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        ShopEntity item =this.shopService.getItem(id);
        this.shopService.delete(item);

        return "redirect:/shop";
    }
//    @GetMapping("/search")
//    public String searchItems(@RequestParam("keyword") String keyword, Model model) {
//        List<ShopEntity> searchResults = shopService.searchItem(keyword);
//        System.out.println("키워드"+keyword);
//        System.out.println("서치결과" + searchResults);
//        model.addAttribute("searchResults", searchResults);
//        return "list/Shop/shopSearchResults"; // 검색 결과를 표시할 Thymeleaf 템플릿 이름
//    }
    @GetMapping("/shop/search")
    public String searchItems(@RequestParam("keyword") String keyword,@RequestParam(value = "page",defaultValue = "0")int page, Model model) {
        Page<ShopEntity> searchResults = this.shopService.searchItem(keyword,page);
        model.addAttribute("searchResults", searchResults);
        model.addAttribute("keyword", keyword);

        return "list/Shop/shopSearchResults"; // 검색 결과를 표시할 Thymeleaf 템플릿 이름
    }

}
