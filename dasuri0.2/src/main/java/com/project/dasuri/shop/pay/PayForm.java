package com.project.dasuri.shop.pay;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PayForm {
    private Long id;

    @NotEmpty(message = "구매자는 필수항목입니다.")
    private String payusername;

    private String userId;

    @NotEmpty(message = "요청사항을 적어주세요")
    private String payrequest;

    @NotEmpty(message = "우편번호를 적어주세요")
    private String userPostcode;

    @NotEmpty(message = "주소를 적어주세요")
    private String userAddress;

    private String userDetailaddress;

    private String userExtraaddress;

    @NotEmpty(message = "연락처를 적어주세요")
    private String number;

    private LocalDateTime payday;

    private String itemname;
    private String price;

    private int paycheck;
}