package com.project.dasuri.shop;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shop")
public class ShopController {
    private final ShopService shopService;
    @GetMapping("")
    public String shop(Model model){
        List<ShopEntity> itemlist = shopService.itemlist();
        model.addAttribute("itemlist",itemlist);
        return "list/Shop/shop";
    }
    @GetMapping("/create")
    public String createForm(ShopForm shopForm, Model model){

        return  "list/Shop/shopForm";
    }

    @PostMapping("/create")
    public String itemCreate(@Valid ShopForm shopForm, BindingResult bindingResult, MultipartFile file) throws IOException {
        if(bindingResult.hasErrors()){
            return "list/Shop/shopForm";
        }
        this.shopService.create(shopForm.getId(),shopForm.getItemname(),
                shopForm.getPrice(),shopForm.getSeller(),
                shopForm.getDeliveryinfo(),shopForm.getShortinfo(),shopForm.getIteminfo(),file);
        System.out.println("겟아이디 "+shopForm.getId()) ;
        return "redirect:/shop";
    }
    @GetMapping(value = "/detail/{id}")
    public String detail(@PathVariable("id") Long id,Model model){
        ShopEntity item = this.shopService.getItem(id);
        model.addAttribute("item",item);
     return "list/Shop/itemDetail";

    }
    @GetMapping("/modify/{id}")
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
    @PostMapping("/modify/{id}")
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

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        ShopEntity item =this.shopService.getItem(id);
        this.shopService.delete(item);

        return "redirect:/shop";
    }
//
//    @GetMapping("/modify/{id}")
//    public String itemModify(@Valid ShopForm shopForm,BindingResult bindingResult,@PathVariable("id")Long id){
//        if (bindingResult.hasErrors()) {
//            return "list/Shop/shopForm";
//        }
//        ShopEntity item = this.shopService.getItem(id);
//        this.shopService.modify(item,shopForm.getItemname(),shopForm.getPrice(),shopForm.getSeller(),shopForm.getDeliveryinfo());
//        return String.format("redirect:/list/Shop/itemDetail/%s",id);
//
//    }
}
