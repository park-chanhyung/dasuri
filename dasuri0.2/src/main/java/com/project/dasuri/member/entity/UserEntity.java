package com.project.dasuri.member.entity;

import com.project.dasuri.member.dto.UserDTO;
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
@Table(name = "user_table")
//public class UserEntity extends BaseEntity {
public class UserEntity {
    @Id
    private String userId;
    @Column
    private String userName;
    @Column(unique = true)
    private String userNickname;
    @Column
    private String userPwd;
    @Column(unique = true)
    private String userPhone;
    @Column
    private String userPostcode;
    @Column
    private String userAddress;
    @Column
    private String userDetailaddress;
    @Column
    private String userExtraaddress;

    @CreatedDate
    private LocalDateTime signupDate;

//    @Embedded
//    private UserAddress userAddr;

    public static UserEntity toUserEntity(UserDTO userDTO){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userDTO.getUserId());
        userEntity.setUserName(userDTO.getUserName());
        userEntity.setUserNickname(userDTO.getUserNickname());
        userEntity.setUserPwd(userDTO.getUserPwd());
        userEntity.setUserPhone(userDTO.getUserPhone());
//        userEntity.setUserAddr(userDTO.getUserAddr());
        userEntity.setUserPostcode(userDTO.getUserPostcode());
        userEntity.setUserAddress(userDTO.getUserAddress());
        userEntity.setUserDetailaddress(userDTO.getUserDetailaddress());
        userEntity.setUserExtraaddress(userDTO.getUserExtraaddress());

        return userEntity;
    }
}
