package com.project.dasuri.community.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "community_file_table")
public class CommunityFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String originalFileName;

    @Column
    private String storedFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commu_id")
    //게시글 하나에 파일이 여러개 o => 1:N
    // communityFile 입장에서 N:1
    private CommunityEntity communityEntity;


    public static CommunityFileEntity toCommunityFileEntity(CommunityEntity communityEntity, String originalFileName, String storedFileName) {
        CommunityFileEntity communityFileEnity = new CommunityFileEntity();
        communityFileEnity.setOriginalFileName(originalFileName);
        communityFileEnity.setStoredFileName(storedFileName);
        communityFileEnity.setCommunityEntity(communityEntity); //pk가 아니라 부모 엔티티 객체를넘겨줘야한다
        return communityFileEnity;
    }
}
