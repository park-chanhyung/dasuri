package com.project.dasuri.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocCount {
//    지역별 회원 수 도출

    private String loc; //지역 이름
    private Long amount; //지역별 회원 수

}
