package com.project.dasuri.shop.entity;

import com.project.dasuri.member.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "pay_review")
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String comment;

    @Column
    private String star; // 별점을 저장할 필드 추가

    private LocalDateTime createTime;

    @ManyToOne
    private ShopEntity shopEntity;

    @ManyToOne
    private UserEntity author;

}

