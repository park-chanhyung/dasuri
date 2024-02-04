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

    //    지역별 고객 수 top3
    public List<LocCount> top3Users() {
        String[] locals = {
                "강서구", "금정구", "남구", "동구", "동래구",
                "부산진구", "북구", "사상구", "사하구", "서구",
                "수영구", "연제구", "영도구", "중구", "해운대구"
        };

        List<LocCount> locCounts = new ArrayList<>();
        for (int i = 0; i < locals.length ; i++) {
            LocCount locCount = new LocCount();
            locCount.setLoc(locals[i]);
            locCount.setAmount(userRepository.countByUserAddressContaining(locals[i]));
            locCounts.add(locCount);
        }
        locCounts.sort(Comparator.comparingLong(LocCount::getAmount).reversed());

        return locCounts;
    }
}
