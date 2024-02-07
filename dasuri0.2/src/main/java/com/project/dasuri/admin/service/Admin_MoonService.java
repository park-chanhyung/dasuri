package com.project.dasuri.admin.service;

import com.project.dasuri.admin.dto.MoonDTO;
import com.project.dasuri.admin.dto.NoticeDTO;
import com.project.dasuri.admin.entity.MoonEntity;
import com.project.dasuri.admin.entity.NoticeEntity;
import com.project.dasuri.admin.repository.MoonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class Admin_MoonService {
    private final MoonRepository moonRepository;

    //    문의글 리스트 불러오기 (페이징)
    public Page<MoonDTO> admin_paging(Pageable pageable){
        int page = pageable.getPageNumber() -1; //page 값은 0부터 시작하므로 1 뺌 (디폴트 1 요청 시 -1)
        int pageLimit = 7; // 한 페이지당 글 7개

//        문의글 고유번호 기준으로 내림차순 (최신순)
        Page<MoonEntity> moonEntities =
                moonRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "moonPkId")));

//        목록에 보여질 항목
        Page<MoonDTO> moonDTOS = moonEntities.map
                (moon -> new MoonDTO(moon.getMoonUserId(),moon.getMoonRole(),moon.getMoonType(),moon.getMoonStatus(),moon.getMoonTitle(),moon.getMoonQuestionDate(),moon.getMoonPkId()));
        return moonDTOS;
    }

//    문의글
    public MoonDTO findByMoonPkId(Long id){
        Optional<MoonEntity> optionalMoonEntity = moonRepository.findByMoonPkId(id);
        if (optionalMoonEntity.isPresent()){
            return MoonDTO.toMoonDTO(optionalMoonEntity.get());
        }else {
            return null;
        }
    }

//    내 아이디로 문의글 불러오기
//    public List<MoonDTO> findByMoonUserId(String id){
//        List<MoonEntity> moonEntities = moonRepository.findByMoonUserId(id);
//        List<MoonDTO> moonDTOS = new ArrayList<>();
//        for (MoonEntity moonEntity : moonEntities) {
//            moonDTOS.add(MoonDTO.toMoonDTO(moonEntity));
//        }
//        return moonDTOS;
//    }

//    문의글 검색
    public Page<MoonDTO> moonSearch(String keyword, Pageable pageable){

        int page = pageable.getPageNumber() -1; //page 값은 0부터 시작하므로 1 뺌 (디폴트 1 요청 시 -1)
        int pageLimit = 7; // 한 페이지당 글 7개

//        검색결과 페이징 - 문의글 고유번호 기준으로 내림차순 (최신순)
        Page<MoonEntity> moonEntities =
                moonRepository.findByMoonUserIdContainingOrMoonQuestionContainingOrMoonAnswerContainingOrMoonTitleContaining(keyword,keyword,keyword,keyword,PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "moonPkId")));

//        목록에 보여질 항목
        Page<MoonDTO> moonDTOS = moonEntities.map
                (moon -> new MoonDTO(moon.getMoonUserId(),moon.getMoonRole(),moon.getMoonType(),moon.getMoonStatus(),moon.getMoonTitle(),moon.getMoonQuestionDate(),moon.getMoonPkId()));

        return moonDTOS;
    }

//    문의글 상태 구분
    public Page<MoonDTO> moonStatus(String status, Pageable pageable){

        int page = pageable.getPageNumber() -1; //page 값은 0부터 시작하므로 1 뺌 (디폴트 1 요청 시 -1)
        int pageLimit = 7; // 한 페이지당 글 7개

//        문의상태 분리 페이징
        Page<MoonEntity> moonEntities =
                moonRepository.findByMoonStatusContaining(status,PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "moonPkId")));

//        목록에 보여질 항목
        Page<MoonDTO> moonDTOS = moonEntities.map
                (moon -> new MoonDTO(moon.getMoonUserId(),moon.getMoonRole(),moon.getMoonType(),moon.getMoonStatus(),moon.getMoonTitle(),moon.getMoonQuestionDate(),moon.getMoonPkId()));

        return moonDTOS;
    }
}
