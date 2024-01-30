package com.project.dasuri.member.dto;

import com.project.dasuri.member.entity.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDTO {
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

        return userDTO;
    }
}
