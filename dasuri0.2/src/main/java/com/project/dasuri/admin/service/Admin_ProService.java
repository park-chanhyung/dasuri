package com.project.dasuri.admin.service;

import com.project.dasuri.admin.repository.AdminProRepository;
import com.project.dasuri.member.dto.ProDTO;
import com.project.dasuri.member.dto.UserDTO;
import com.project.dasuri.member.entity.ProEntity;
import com.project.dasuri.member.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Admin_ProService {
    private final AdminProRepository proRepository;

    //    기사리스트 - 페이징
    public Page<ProDTO> admin_paging(Pageable pageable){
        int page = pageable.getPageNumber() -1; //page 값은 0부터 시작하므로 1 뺌 (디폴트 1 요청 시 0으로 시작)
        int pageLimit = 7; // 한 페이지당 글 7개

//        기사리스트 고유번호(num) 기준으로 내림차순
        Page<ProEntity> proEntities =
                proRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "num")));

//        목록에 보여질 항목
//        아이디,이름,업체명,활동지역,가입일,정지여부
        Page<ProDTO> proDTOS = proEntities.map
                (pro -> new ProDTO(pro.getProId(),pro.getProName(),pro.getProNickname(),pro.getProLegions(),pro.getSignupDate(),pro.getSuspensionExpiry()));

        return proDTOS;
    }

    //    기사정보 조회
    public ProDTO findByProId(String proId){
        Optional<ProEntity> proEntity = proRepository.findByProId(proId);
        if (proEntity.isPresent()){
            return ProDTO.toProDTO(proEntity.get());
        }else {
            return null;
        }
    }

    //    기사 수
    public Long proCount(){
        return proRepository.count();
    }

    //    오늘부터 -6일 전까지 가입한 기사 수
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

        counts.add(proRepository.countBySignupDateBetween(now, now.plusDays(1))); // 오늘
        counts.add(proRepository.countBySignupDateBetween(day1, day1.plusDays(1))); // 어제
        counts.add(proRepository.countBySignupDateBetween(day2, day2.plusDays(1)));
        counts.add(proRepository.countBySignupDateBetween(day3, day3.plusDays(1)));
        counts.add(proRepository.countBySignupDateBetween(day4, day4.plusDays(1)));
        counts.add(proRepository.countBySignupDateBetween(day5, day5.plusDays(1)));
        counts.add(proRepository.countBySignupDateBetween(day6, day6.plusDays(1)));

        return counts;
    }

    //    기사 검색
    public Page<ProDTO> proSearch(String keyword, Pageable pageable){
        int page = pageable.getPageNumber() -1; //page 값은 0부터 시작하므로 1 뺌 (디폴트 1 요청 시 -1)
        int pageLimit = 7; // 한 페이지당 글 7개

//        검색결과 페이징 - 기사 고유번호(num) 기준으로 내림차순
        Page<ProEntity> proEntities =
                proRepository.findByProIdContainingOrProNameContainingOrProNicknameContainingOrProLegionsContaining(keyword,keyword,keyword,keyword,PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "num")));

//        목록에 보여질 항목
//        아이디,이름,닉네임,주소,유형,가입일,정지여부
        Page<ProDTO> proDTOS = proEntities.map
                (pro -> new ProDTO(pro.getProId(),pro.getProName(),pro.getProNickname(),pro.getProLegions(),pro.getSignupDate(),pro.getSuspensionExpiry()));

        return proDTOS;
    }

}
