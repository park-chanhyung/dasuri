package com.project.dasuri.community.entity;

import com.project.dasuri.community.dto.CommunityDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

//DB의 테이블 역할을 하는 클래스
@Entity
@Getter
@Setter
@Table(name = "community")
public class CommunityEntity extends TimeEntity {
    @Id // pk 컬럼 지정. 필수
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    @Column(length = 20, nullable = false) // 크기 20, not null
    private String commuWriter;

    @Column
    private String commuTitle;

    @Column(length = 500)
    private String commuContents;

    @Column
    private int commuHits;

    public static CommunityEntity toSaveEntity (CommunityDto communityDto){
        CommunityEntity communityEntity = new CommunityEntity();
        communityEntity.setCommuWriter(communityDto.getCommuWriter());
        communityEntity.setCommuTitle(communityDto.getCommuTitle());
        communityEntity.setCommuContents(communityDto.getCommuContents());
        communityEntity.setCommuHits(0);
        return communityEntity;
    }

//    @Column
//    private int fileAttached; // 1 or 0
//
//    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
//    private List<BoardFileEntity> boardFileEntityList = new ArrayList<>();
//
//    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
//    private List<CommentEntity> commentEntityList = new ArrayList<>();


}
