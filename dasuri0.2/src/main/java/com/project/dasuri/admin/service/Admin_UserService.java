package com.project.dasuri.admin.service;

import com.project.dasuri.admin.dto.MoonDTO;
import com.project.dasuri.admin.entity.MoonEntity;
import com.project.dasuri.admin.repository.AdminUserRepository;
import com.project.dasuri.member.dto.UserDTO;
import com.project.dasuri.member.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class Admin_UserService {

    private final AdminUserRepository userRepository;

    //    고객리스트 (페이징)
    public Page<UserDTO> admin_paging(Pageable pageable){

        int page = pageable.getPageNumber() -1; //page 값은 0부터 시작하므로 1 뺌 (디폴트 1 요청 시 0으로 시작)
        int pageLimit = 7; // 한 페이지당 글 7개

//        유저리스트 고유번호(num) 기준으로 내림차순
        Page<UserEntity> userEntities =
                userRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "num")));

//        목록에 보여질 항목
//        아이디,이름,닉네임,주소,유형,가입일,정지여부
        Page<UserDTO> userDTOS = userEntities.map
                (user -> new UserDTO(user.getUserId(), user.getUserName(), user.getUserNickname(), user.getUserAddress(), user.getRole(), user.getSignupDate(),user.getSuspensionExpiry()));

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

    //    고객 검색
    public Page<UserDTO> userSearch(String keyword, Pageable pageable){
        int page = pageable.getPageNumber() -1; //page 값은 0부터 시작하므로 1 뺌 (디폴트 1 요청 시 -1)
        int pageLimit = 7; // 한 페이지당 글 7개

//        검색결과 페이징 - 고객 고유번호(num) 기준으로 내림차순
        Page<UserEntity> userEntities =
                userRepository.findByUserIdContainingOrUserNameContainingOrUserNicknameContainingOrUserAddressContaining(keyword,keyword,keyword,keyword,PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "num")));

//        목록에 보여질 항목
//        아이디,이름,닉네임,주소,유형,가입일,정지여부
        Page<UserDTO> userDTOS = userEntities.map
                (user -> new UserDTO(user.getUserId(), user.getUserName(), user.getUserNickname(), user.getUserAddress(), user.getRole(), user.getSignupDate(),user.getSuspensionExpiry()));

        return userDTOS;
    }

}
