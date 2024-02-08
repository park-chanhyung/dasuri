package com.project.dasuri.shop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="pay")
public class PayEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String userId;  // 사는사람 (로그인한사람)
    @Column
    private String price; //기갹

    @Column
    private String itemname;


    @Column
    private String payusername;  // 받는사람

    @Column
    private String payrequest;  // 요청사항

    @Column
    private String number; // 연락처

    @Column
    private LocalDateTime payday;  // 결제일

    @Column
    private String userPostcode;
    @Column
    private String userAddress;
    @Column
    private String userDetailaddress;
    @Column
    private String userExtraaddress;

    @Column
    private int paycheck;

}
