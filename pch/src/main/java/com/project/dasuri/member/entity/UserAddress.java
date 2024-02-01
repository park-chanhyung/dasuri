package com.project.dasuri.member.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class UserAddress {
    private String userPostcode;
    private String userAddress;
    private String userDetailaddress;
    private String userExtraaddress;

}
