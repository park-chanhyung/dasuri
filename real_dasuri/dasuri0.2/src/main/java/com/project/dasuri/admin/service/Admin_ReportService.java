package com.project.dasuri.admin.service;

import com.project.dasuri.admin.dto.TodayReport;
import com.project.dasuri.admin.repository.AdminCommRepository;
import com.project.dasuri.admin.repository.AdminProRepository;
import com.project.dasuri.admin.repository.AdminUserRepository;
import com.project.dasuri.admin.repository.MoonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class Admin_ReportService {
    private final AdminCommRepository adminCommRepository; //일간 게시물 카운트용
    private final AdminUserRepository adminUserRepository; //일간 가입 카운트용 (고객)
    private final AdminProRepository proRepository; //일간 가입 카운트용 (기사)
    private final MoonRepository moonRepository; //일간 문의 카운트용

//    일간 리포트
    public TodayReport todayReport(){
        TodayReport todayReport = new TodayReport();

//        일간 게시물 수
        LocalDateTime now = LocalDateTime.now(); //지금
        LocalDateTime startOfDay = LocalDateTime.of(LocalDate.now(), LocalTime.MIN); //오늘 시작점
        todayReport.setCommuCount(adminCommRepository.countByCreatedTimeBetween(startOfDay, now));

//        일간 가입 카운트
        Long user = adminUserRepository.countBySignupDateBetween(startOfDay,now);
        Long pro = proRepository.countBySignupDateBetween(startOfDay,now);
        todayReport.setSignCount(user+pro);

//        일간 문의글 수
        todayReport.setMoonCount(moonRepository.countByMoonQuestionDateBetween(startOfDay,now));

        return todayReport;
    }

}
