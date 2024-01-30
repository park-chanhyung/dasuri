package com.project.dasuri.admin.service;

import com.project.dasuri.admin.dto.NoticeDTO;
import com.project.dasuri.admin.entity.NoticeEntity;
import com.project.dasuri.admin.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;

//    공지글 신규 등록
//    1. 폼에서 받은 dto객체를 엔티티 객체로 변환
//    2. repository의 save 메소드로 DB에 저장 (엔티티 객체를 넘겨야함)
    public void save(NoticeDTO noticeDTO){
        NoticeEntity noticeEntity = NoticeEntity.toNoticeEntity(noticeDTO);
        noticeRepository.save(noticeEntity);
    }

//    공지 리스트 불러오기
    public List<NoticeDTO> findAll(){
        List<NoticeEntity> noticeEntities = noticeRepository.findAll();
        List<NoticeDTO> noticeDTOS = new ArrayList<>();
        for (NoticeEntity noticeEntity : noticeEntities) {
            noticeDTOS.add(NoticeDTO.toNoticeDTO(noticeEntity));
        }
        return noticeDTOS;
    }

//    공지 불러오기
    public NoticeDTO findByNoticeId(Long id){
        System.out.println("*** id : "+id);
        Optional<NoticeEntity> optionalNoticeEntity = noticeRepository.findByNoticeId(id);
        if (optionalNoticeEntity.isPresent()){
            return NoticeDTO.toNoticeDTO(optionalNoticeEntity.get());
        }else {
            return null;
        }
    }

    public void update (NoticeDTO noticeDTO){
        NoticeEntity noticeEntity = NoticeEntity.toUpdateNoticeEntity(noticeDTO);
        noticeRepository.save(noticeEntity);
    }
}
