package com.project.dasuri.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodayReport {

//    관리자페이지 푸터 > 일간 리포트 객체

    private Long commuCount;
    private Long moonCount;
    private Long signCount;
}
