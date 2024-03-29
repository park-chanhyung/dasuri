package com.project.dasuri.member.entity;

import com.project.dasuri.member.dto.ProDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "pro_table")
public class ProEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int num;
    @Column
    private String proId;
    @Column
    private String proName;
    @Column
    private String proNickname;
    @Column
    private String proPwd;
    @Column
    private String proPhone;
    @Column
    private String proLegions;
    @Column
    private String role;

    @CreatedDate
    private LocalDateTime signupDate;

    public static ProEntity toProEntity(ProDTO proDTO){
        ProEntity proEntity = new ProEntity();

        proEntity.setProId(proDTO.getProId());
        proEntity.setProName(proDTO.getProName());
        proEntity.setProNickname(proDTO.getProNickname());
        proEntity.setProPwd(proDTO.getProPwd());
        proEntity.setProPhone(proDTO.getProPhone());
        proEntity.setProLegions(proDTO.getProLegions());
        proEntity.setRole("ROLE_PRO");

        return proEntity;
    }
}
