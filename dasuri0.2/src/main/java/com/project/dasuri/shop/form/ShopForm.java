package com.project.dasuri.shop.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ShopForm {
    private Long id;

    @NotEmpty(message = "제목은 필수항목입니다.")
    @Size(max=100)
    private String itemname;

    @NotEmpty(message = "판매자는 필수항목입니다.")
    private String seller;

    @NotEmpty( message = "최소가격은 1000원입니다.")
    @Pattern(regexp="^[0-9]+$", message="숫자만 입력해주세요.")
    private String price;

    @NotEmpty(message = "배송정보는 필수항목입니다.")
    private String deliveryinfo;

    @NotEmpty(message = "한줄소개를 적어주세요. 이건 카드에 들어갈 내용입니다.")
    private String shortinfo;


    @NotEmpty(message = "한줄소개를 적어주세요 이건 카드에 들어갈 내용입니다.")
    private String iteminfo;
    // 추가된 부분
    private MultipartFile file;

}
