package com.project.dasuri.member.entity;

import com.project.dasuri.member.dto.UserDTO;
import com.project.dasuri.member.repository.UserRepository;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user_table")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int num;
    @Column
    private String userId;
    @Column
    private String userName;
    @Column
    private String userNickname;
    @Column
    private String userPwd;
    @Column
    private String userPhone;
    @Column
    private String userPostcode;
    @Column
    private String userAddress;
    @Column
    private String userDetailaddress;
    @Column
    private String userExtraaddress;
    @Column
    private String role;

    @CreatedDate
    private LocalDateTime signupDate;

//    @Embedded
//    private UserAddress userAddr;

    public static UserEntity toUserEntity(UserDTO userDTO){

//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userDTO.getUserId());
        userEntity.setUserName(userDTO.getUserName());
        userEntity.setUserNickname(userDTO.getUserNickname());
//        userEntity.setUserPwd(bCryptPasswordEncoder.encode(userDTO.getUserPwd()));
        userEntity.setUserPwd(userDTO.getUserPwd());
        userEntity.setUserPhone(userDTO.getUserPhone());
//        userEntity.setUserAddr(userDTO.getUserAddr());
        userEntity.setUserPostcode(userDTO.getUserPostcode());
        userEntity.setUserAddress(userDTO.getUserAddress());
        userEntity.setUserDetailaddress(userDTO.getUserDetailaddress());
        userEntity.setUserExtraaddress(userDTO.getUserExtraaddress());
        userEntity.setRole("ROLE_USER");

        return userEntity;
    }
}
