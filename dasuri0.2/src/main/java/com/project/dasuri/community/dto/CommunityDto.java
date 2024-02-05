package com.project.dasuri.community.dto;

import com.project.dasuri.community.entity.CommunityEntity;
import com.project.dasuri.community.entity.CommunityFileEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//DTO(Data Transfer Object)데이터를 전송할때 사용하는 객체, VO, Bean
@Getter
@Setter
@ToString //필드값 확인
@NoArgsConstructor //기본 생성자
@AllArgsConstructor //모든 필드를 매개변수로 하는 생성자
public class CommunityDto {
    private Long id;

    private String userID; //커뮤니티에 글 쓰는 회원
    private String commuWriter;
    private String commuTitle;
    private String commuContents;
    private int commuHits;
    private LocalDateTime commuCreatedTime;
    private LocalDateTime comumUpdatedTime;
    private String role;

    private String adminDeleted;//admin이 게시글 삭제



    private List<MultipartFile> communityFile;  //community_post --> Controller 파일 담는 용도
    private List<String> originalFileName; //원본 파일이름
    private List<String> storedFileName; //서버 저장 파일 이름
    private int fileAttached; // 파일 첨부 여부(첨부 1, 미첨부 0)

    public CommunityDto(Long id, String commuWriter, String commuTitle, int commuHits, LocalDateTime commuCreatedTime, String userId, String role) {
        this.id = id;
        this.commuWriter = commuWriter;
        this.commuTitle = commuTitle;
        this.commuHits = commuHits;
        this.commuCreatedTime = commuCreatedTime;
        this.userID = userId; //사용자ID
        this.role = role; //역할
    }


    public static CommunityDto toCommunityDto(CommunityEntity communityEntity){
        CommunityDto communityDto = new CommunityDto();
        communityDto.setId(communityEntity.getId());
        communityDto.setCommuWriter(communityEntity.getCommuWriter());
        communityDto.setCommuTitle(communityEntity.getCommuTitle());
        communityDto.setCommuContents(communityEntity.getCommuContents());
        communityDto.setCommuHits(communityEntity.getCommuHits());
        communityDto.setCommuCreatedTime(communityEntity.getCreatedTime());
        communityDto.setComumUpdatedTime(communityEntity.getUpdatedTime());
//        communityDto.setUserID(communityEntity.getUserId()); //유저 아이디
//        communityDto.setRole(communityEntity.getRole());//role

        if (communityEntity.getFileAttached() == 0){ //파일이 없는경우
            communityDto.setFileAttached(communityEntity.getFileAttached());//0
        }else {//있는 경우
            List<String> originalFileNameList = new ArrayList<>();
            List<String> storedFileNameList = new ArrayList<>();
            communityDto.setFileAttached(communityEntity.getFileAttached());//1
            //파일 이름을 가져가야함
            // orginalFileName, storedFileName : board_file_table(BoardFileEntity)
            // join
            // select * from board_table b, board_file_table bf where b.id=bf.board_id
            // and where b.id=?
            for (CommunityFileEntity communityFileEntity: communityEntity.getCommunityFileEnityList()){
                originalFileNameList.add(communityFileEntity.getOriginalFileName());
                storedFileNameList.add(communityFileEntity.getStoredFileName());
            }
            communityDto.setOriginalFileName(originalFileNameList);
            communityDto.setStoredFileName(storedFileNameList);
//            communityDto.setOriginalFileName(communityEntity.getCommunityFileEnityList().get(0).getOriginalFileName());
//            communityDto.setStoredFileName(communityEntity.getCommunityFileEnityList().get(0).getStoredFileName());
        }

        return  communityDto;
    }


}
