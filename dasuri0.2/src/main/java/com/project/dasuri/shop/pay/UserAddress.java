package com.project.dasuri.shop.pay;

import jakarta.persistence.Embeddable;

@Embeddable
public class UserAddress {
    private String userPostcode;
    private String userAddress;
    private String userDetailaddress;
    private String userExtraaddress;

}
