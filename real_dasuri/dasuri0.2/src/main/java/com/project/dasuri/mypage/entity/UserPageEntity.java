package com.project.dasuri.mypage.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "userpage_table")
public class UserPageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //    첨부파일 이름 (uuid)
    @Column(nullable = true)
    private String filename;

    //    첨부파일 경로
    @Column(nullable = true)
    private String filePath;
}
