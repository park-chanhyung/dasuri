package com.project.dasuri.shop;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="item")
public class ShopEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String itemname;
    private LocalDateTime regtime;

    @Column
    private String price;

//  파는사람이 아니고 브랜드 느낌  관리자만 글작성할수잇슴.
    @Column
    private String seller;

    @Column
    private String deliveryinfo;

    @Column
    private String shortinfo;

    @Column
    private String iteminfo;

    @Column
    private String filename;

    @Column
    private String filePath;
}
