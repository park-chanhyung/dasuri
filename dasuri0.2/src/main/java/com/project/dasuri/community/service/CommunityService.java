package com.project.dasuri.community.service;

import com.project.dasuri.community.dto.CommunityDto;
import com.project.dasuri.community.entity.CommunityEntity;
import com.project.dasuri.community.entity.CommunityFileEntity;
import com.project.dasuri.community.repository.CommunityFileRepository;
import com.project.dasuri.community.repository.CommunityRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//DTO -> Entity (Entuty Class)
//Entity -> DTO (DTO Class)
@Service
@RequiredArgsConstructor

public class CommunityService {
    private  final CommunityRepository communityRepository;
    private  final CommunityFileRepository communityFileRepository;

    public void save(CommunityDto communityDto) throws IOException {
        // 파일 첨부 여부에 따라 로직 분리
        if (communityDto.getCommunityFile().isEmpty()) {
            // 첨부 파일 없음.
            CommunityEntity communityEntity = CommunityEntity.toSaveEntity(communityDto);
            communityRepository.save(communityEntity);
        } else {
            // 첨부 파일 있음.
             /*
                1. DTO에 담긴 파일을 꺼냄
                2. 파일의 이름 가져옴
                3. 서버 저장용 이름을 만듦
                // 내사진.jpg => 839798375892_내사진.jpg
                4. 저장 경로 설정
                5. 해당 경로에 파일 저장
                6. board_table에 해당 데이터 save 처리
                7. board_file_table에 해당 데이터 save 처리
             */
            CommunityEntity communityEntity = CommunityEntity.toSaveFileEntity(communityDto);
            Long savedId = communityRepository.save(communityEntity).getId();
            CommunityEntity community = communityRepository.findById(savedId).get();

            for (MultipartFile communityFile : communityDto.getCommunityFile()) {
//                MultipartFile communityFile = communityDto.getCommunityFile(); // 1.
                String originalFilename = communityFile.getOriginalFilename(); // 2.
                String storedFileName = System.currentTimeMillis() + "_" + originalFilename; // 3.
                String savePath = "C:/commuinity_img/" + storedFileName; // 4. C:/springboot_img/9802398403948_내사진.jpg
//            String savePath = "/Users/사용자이름/springboot_img/" + storedFileName; // C:/springboot_img/9802398403948_내사진.jpg
                communityFile.transferTo(new File(savePath)); // 5.

                CommunityFileEntity communityFileEntity = CommunityFileEntity.toCommunityFileEntity(community, originalFilename, storedFileName);
                communityFileRepository.save(communityFileEntity);

            }
        }
    }


//    리스트로 들어가있는 값 찾기
    @Transactional
    public List<CommunityDto> findAll() {
       List<CommunityEntity> communityEntityList = communityRepository.findAll();
       List<CommunityDto> communityDtoList = new ArrayList<>();
       for(CommunityEntity communityEntity: communityEntityList){
           communityDtoList.add(CommunityDto.toCommunityDto(communityEntity));
       }
       return communityDtoList;
    }


//    조회수증가
    @Transactional
    public void updateHits(Long id) {
        communityRepository.updateHits(id);
    }
    @Transactional
    public CommunityDto findById(Long id){
        Optional<CommunityEntity> optionalCommunityEntity = communityRepository.findById(id);
        if(optionalCommunityEntity.isPresent()){
            CommunityEntity communityEntity = optionalCommunityEntity.get();
            CommunityDto communityDto = CommunityDto.toCommunityDto(communityEntity);
            return communityDto;
        }else{
            return null;
        }
    }

//    업데이트
    public CommunityDto update(CommunityDto communityDto) {
        CommunityEntity communityEntity =CommunityEntity.toUpdateEntity(communityDto);
        communityRepository.save(communityEntity);

        return findById(communityDto.getId());
    }

    //삭제
    public void delete(Long id){
        communityRepository.deleteById(id);
    }

    public Page<CommunityDto> paging(Pageable pageable) {
        int page = pageable.getPageNumber()-1;
        int pageLimit = 10; //한페이지에 보여줄 글 갯수
//        한페이지당 3개씩 글을 보여주고 정렬 기준은 id 기준으로 내림차순 정렬
//        page 위치에 있는 값은 0부터 시작
        Page<CommunityEntity> communityEntities =
                communityRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC,"id")));
//        목록: id, wirter, title, hits, createTime
        Page<CommunityDto> communityDtos = communityEntities.map(community ->new CommunityDto(community.getId(),
                community.getCommuWriter(),community.getCommuTitle(),community.getCommuHits(),
                community.getCreatedTime(), community.getUserId(), community.getRole())); //userID, Role 추가
        return communityDtos;
    }

    //검색
    public List<CommunityDto> searchNo(String keyword){
        List<CommunityEntity> communityEntities = communityRepository.findBycommuTitleContainingOrUserIdContainingOrderByIdDesc(keyword,keyword);
        List<CommunityDto> communityDtos = new ArrayList<>();
        int x = communityEntities.size();
        for(CommunityEntity communityEntity : communityEntities){
            CommunityDto communityDto = CommunityDto.toCommunityDto(communityEntity);
            communityDto.setCommunity_no(x); //검색한후 게시판 번호매김
            communityDtos.add(communityDto);
            x--;
        }
        return communityDtos;
    }
}
