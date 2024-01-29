package com.project.dasuri.member.dto;

import com.project.dasuri.member.entity.UserEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDTO {

    @NotBlank(message = "아이디 입력")
    private String userId;
    private String userName;
    private String userNickname;
    private String userPwd;
    private String userPhone;
//    private UserAddress userAddr;
    private String userPostcode;
    private String userAddress;
    private String userDetailaddress;
    private String userExtraaddress;
    private String role;

    public static UserDTO toUserDTO(UserEntity userEntity){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(userEntity.getUserId());
        userDTO.setUserName(userEntity.getUserName());
        userDTO.setUserNickname(userEntity.getUserNickname());
        userDTO.setUserPwd(userEntity.getUserPwd());
        userDTO.setUserPhone(userEntity.getUserPhone());
//        userDTO.setUserAddr(userEntity.getUserAddr());
        userDTO.setUserPostcode(userEntity.getUserPostcode());
        userDTO.setUserAddress(userEntity.getUserAddress());
        userDTO.setUserDetailaddress(userEntity.getUserDetailaddress());
        userDTO.setUserExtraaddress(userEntity.getUserExtraaddress());
        userDTO.setRole(userEntity.getRole());

        return userDTO;
    }
}
