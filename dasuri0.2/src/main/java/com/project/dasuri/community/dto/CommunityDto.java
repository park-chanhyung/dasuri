package com.project.dasuri.community.dto;

import com.project.dasuri.community.entity.CommunityEntity;
import lombok.*;

import java.time.LocalDateTime;

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
    public CommunityDto(Long id, String commuWriter, String commuTitle, int commuHits, LocalDateTime commuCreatedTime) {
        this.id = id;
        this.commuWriter = commuWriter;
        this.commuTitle = commuTitle;
        this.commuHits = commuHits;
        this.commuCreatedTime = commuCreatedTime;
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
        communityDto.setUserID(communityEntity.getUserId()); //유저 아이디
        communityDto.setRole(communityEntity.getRole());//role
        return  communityDto;
    }


}
