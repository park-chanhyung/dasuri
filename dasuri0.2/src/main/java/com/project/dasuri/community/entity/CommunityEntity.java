package com.project.dasuri.community.entity;

import com.project.dasuri.community.dto.CommunityDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;

//DB의 테이블 역할을 하는 클래스
@Entity
@Getter
@Setter
@Table(name = "community")
@SQLDelete(sql = "UPDATE community SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class CommunityEntity extends TimeEntity {
    @Id // pk 컬럼 지정. 필수
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    @Column(name = "id")
    private Long id;

    @Column(length = 20, nullable = false) // 크기 20, not null
    private String commuWriter;

    @Column
    private String commuTitle;

    @Column(length = 500)
    private String commuContents;

    @Column
    private int commuHits;

    @Column
    private String role;

    @Column
    private String userId;

    @Column
    private String userNickname;

    @Column
    private String adminDeleted;

    @Column
    private boolean deleted = Boolean.FALSE;

    @Column
    private Integer fileAttached ; // 1 or 0

//    게시글 : 파일
    @OneToMany(mappedBy = "communityEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CommunityFileEntity> communityFileEnityList = new ArrayList<>();

//    게시글: 댓글
    @OneToMany(mappedBy = "communityEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList = new ArrayList<>();

    public static CommunityEntity toSaveEntity (CommunityDto communityDto){
        CommunityEntity communityEntity = new CommunityEntity();
        communityEntity.setCommuWriter(communityDto.getCommuWriter());
        communityEntity.setCommuTitle(communityDto.getCommuTitle());
        communityEntity.setCommuContents(communityDto.getCommuContents());
        communityEntity.setUserId(communityDto.getUserID()); //새로추가
        communityEntity.setRole(communityDto.getRole()); //role추가
        communityEntity.setCommuHits(0); //조회수 0으로 시작
        communityEntity.setFileAttached(0); //파일없음
        communityEntity.setAdminDeleted(communityDto.getAdminDeleted());
        communityEntity.setUserNickname(communityDto.getUserNickname());//닉네임

        return communityEntity;
    }

    public static CommunityEntity toUpdateEntity(CommunityDto communityDto) {
        CommunityEntity communityEntity = new CommunityEntity();
        communityEntity.setId(communityDto.getId());
        communityEntity.setCommuWriter(communityDto.getCommuWriter());
        communityEntity.setCommuTitle(communityDto.getCommuTitle());
        communityEntity.setCommuContents(communityDto.getCommuContents());
        communityEntity.setCommuHits(communityDto.getCommuHits());
        communityEntity.setUserId(communityDto.getUserID()); //새로추가
        communityEntity.setRole(communityDto.getRole()); //role추가
        communityEntity.setUserNickname(communityDto.getUserNickname());//닉네임
        communityEntity.setAdminDeleted(communityDto.getAdminDeleted());//블라인드여부
        communityEntity.setFileAttached(communityDto.getFileAttached());//파일첨부여부

        return communityEntity;
    }

    public static CommunityEntity toSaveFileEntity(CommunityDto communityDto) {
        CommunityEntity communityEntity = new CommunityEntity();
        communityEntity.setCommuWriter(communityDto.getCommuWriter());
        communityEntity.setCommuTitle(communityDto.getCommuTitle());
        communityEntity.setCommuContents(communityDto.getCommuContents());
        communityEntity.setUserId(communityDto.getUserID()); //새로추가
        communityEntity.setRole(communityDto.getRole()); //role추가
        communityEntity.setCommuHits(0); //조회수 0으로 시작
        communityEntity.setFileAttached(1); //파일있음
        communityEntity.setUserNickname(communityDto.getUserNickname());//닉네임

        return communityEntity;
    }



}
