package com.project.dasuri.admin.service;

import com.project.dasuri.admin.dto.LocCount;
import com.project.dasuri.admin.repository.AdminProRepository;
import com.project.dasuri.admin.repository.AdminUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class Admin_LocService {
    private final AdminUserRepository userRepository;
    private final AdminProRepository proRepository;

    //    지역별 고객 수 top5
    public List<LocCount> top5Users() {
        String[] locals = {
                "부산 강서구", "부산 금정구", "부산 남구", "부산 동구", "부산 동래구",
                "부산 부산진구", "부산 북구", "부산 사상구", "부산 사하구", "부산 서구",
                "부산 수영구", "부산 연제구", "부산 영도구", "부산 중구", "부산 해운대구"
        };

        List<LocCount> locCounts = new ArrayList<>();
        for (int i = 0; i < locals.length ; i++) {
            LocCount locCount = new LocCount();
            locCount.setLoc(locals[i].substring(3));
            locCount.setAmount(userRepository.countByUserAddressContaining(locals[i]));
            locCounts.add(locCount);
        }
        locCounts.sort(Comparator.comparingLong(LocCount::getAmount).reversed());

        return locCounts;
    }

    //    지역별 기사 수 top5
    public List<LocCount> top5pros() {
        String[] locals = new String[]{
                "서구", "금정구", "남구", "동구", "동래구",
                "부산진구", "북구", "사상구", "사하구","강서_구",
                "수영구", "연제구", "영도구", "중구", "해운대구"
        };

        List<LocCount> locCounts = new ArrayList<>(); //나머지 지역 기사 수 객체
        for (int i = 0; i < locals.length ; i++) {
            LocCount locCount = new LocCount();
            locCount.setAmount(proRepository.countByProLegionsContaining(locals[i]));
            if (locals[i].equals("강서_구")) {
                locCount.setLoc("강서구");
            }else {
                locCount.setLoc(locals[i]);
            }
            locCounts.add(locCount);
        }

        //내림차순 정렬
        locCounts.sort(Comparator.comparingLong(LocCount::getAmount).reversed());

        return locCounts;
    }
}
