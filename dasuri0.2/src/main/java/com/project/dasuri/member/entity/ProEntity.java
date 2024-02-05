package com.project.dasuri.member.entity;

import com.project.dasuri.member.dto.ProDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "pro_table")
public class ProEntity implements UserDetailEntity{
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
    @Column
    private String birth;


    @CreatedDate
    private LocalDateTime signupDate;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    private LocalDateTime suspensionExpiry; //계정 정지 만료기간


    // UserDetailEntity 인터페이스 구현
    @Override
    public String getRole() {
        return role;
    }

    @Override
    public String getUserPwd() {
        return proPwd;
    }

    @Override
    public String getUserId() {
        return proId;
    }

    @Override
    public LocalDateTime getSuspensionExpiry() {
        return suspensionExpiry;
    }

    public static ProEntity toProEntity(ProDTO proDTO){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        ProEntity proEntity = new ProEntity();

        proEntity.setProId(proDTO.getProId());
        proEntity.setProName(proDTO.getProName());
        proEntity.setProNickname(proDTO.getProNickname());
        proEntity.setProPwd(bCryptPasswordEncoder.encode(proDTO.getProPwd()));
        proEntity.setProPhone(proDTO.getProPhone());
        proEntity.setProLegions(proDTO.getProLegions());
        if(proDTO.getProId().equals("admin")){
            proEntity.setRole("ROLE_ADMIN");
        }else{
            proEntity.setRole("ROLE_PRO");
        }
        proEntity.setBirth(proDTO.getBirth());
        return proEntity;
    }
}
