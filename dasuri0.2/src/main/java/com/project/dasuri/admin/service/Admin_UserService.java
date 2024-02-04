package com.project.dasuri.admin.service;

import com.project.dasuri.admin.repository.AdminUserRepository;
import com.project.dasuri.member.dto.UserDTO;
import com.project.dasuri.member.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class Admin_UserService {

    private final AdminUserRepository userRepository;

    //    고객리스트
    public List<UserDTO> findAll(){
        List<UserEntity> userEntities = userRepository.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        for (UserEntity userEntity : userEntities){
            UserDTO userDTO = UserDTO.toUserDTO(userEntity);
            userDTO.setRole("고객");
            userDTOS.add(userDTO);
        }
        return userDTOS;
    }

    //    고객 정보 조회
    public UserDTO findByUserId(String userId){
        UserDTO userDTO = UserDTO.toUserDTO(userRepository.findByUserId(userId));
        return userDTO;
    }

    //    고객 수
    public Long userCount(){
        return userRepository.count();
    }

//    오늘부터 -6일 전까지 가입한 고객 수
    public List<Long> userSignupDateCount(){
        LocalDateTime now = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);

        LocalDateTime today = now;
        LocalDateTime day1 = now.minusDays(1);
        LocalDateTime day2 = now.minusDays(2);
        LocalDateTime day3 = now.minusDays(3);
        LocalDateTime day4 = now.minusDays(4);
        LocalDateTime day5 = now.minusDays(5);
        LocalDateTime day6 = now.minusDays(6);

        List<Long> counts = new ArrayList<>();

        counts.add(userRepository.countBySignupDateBetween(now, now.plusDays(1))); // 오늘
        counts.add(userRepository.countBySignupDateBetween(day1.minusDays(1), day1)); // 어제
        counts.add(userRepository.countBySignupDateBetween(day2.minusDays(1), day2)); // 2일 전
        counts.add(userRepository.countBySignupDateBetween(day3.minusDays(1), day3)); // 3일 전
        counts.add(userRepository.countBySignupDateBetween(day4.minusDays(1), day4)); // 4일 전
        counts.add(userRepository.countBySignupDateBetween(day5.minusDays(1), day5)); // 5일 전
        counts.add(userRepository.countBySignupDateBetween(day6.minusDays(1), day6)); // 6일 전

        return counts;
    }

}
