package com.project.dasuri.member.entity;

import com.project.dasuri.chat.mysql.MysqlChat;
import com.project.dasuri.member.dto.UserDTO;
import com.project.dasuri.member.repository.UserRepository;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user_table")
public class UserEntity implements UserDetailEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int num;
    @Column(unique = true)
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
    @Column
    private String role;
    @Column
    private String birth;

    @CreatedDate
    private LocalDateTime signupDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    private LocalDateTime suspensionExpiry; // 계정 정지 만료 시간

    @OneToMany(mappedBy = "userEntity",cascade = CascadeType.REMOVE)
    private List<MysqlChat> mysqlChatList;


//    @Embedded
//    private UserAddress userAddr;

    public static UserEntity toUserEntity(UserDTO userDTO){

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userDTO.getUserId());
        userEntity.setUserName(userDTO.getUserName());
        userEntity.setUserNickname(userDTO.getUserNickname());
        userEntity.setUserPwd(bCryptPasswordEncoder.encode(userDTO.getUserPwd()));
//        userEntity.setUserPwd(userDTO.getUserPwd());
        userEntity.setUserPhone(userDTO.getUserPhone());
//        userEntity.setUserAddr(userDTO.getUserAddr());
        userEntity.setUserPostcode(userDTO.getUserPostcode());
        userEntity.setUserAddress(userDTO.getUserAddress());
        userEntity.setUserDetailaddress(userDTO.getUserDetailaddress());
        userEntity.setUserExtraaddress(userDTO.getUserExtraaddress());
        if(userDTO.getUserId().equals("admin")){
            userEntity.setRole("ROLE_ADMIN");
        }else{
            userEntity.setRole("ROLE_USER");
        }
        userEntity.setBirth(userDTO.getBirth());

        return userEntity;
    }
}
