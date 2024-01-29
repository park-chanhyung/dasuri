package com.project.dasuri.member.service;

import com.project.dasuri.member.dto.UserDTO;
import com.project.dasuri.member.entity.UserEntity;
import com.project.dasuri.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    //repository 호출
    private final UserRepository userRepository;

    public void user_signup(UserDTO userDTO){
//        System.out.println("UserService.sign_up");
//        System.out.println("userDTO = " + userDTO);
//        UserEntity userEntity = new UserEntity();
//        userEntity.setUserId(userDTO.getUserId());
//        userEntity.setUserName(userDTO.getUserName());
//        userEntity.setUserNickname(userDTO.getUserNickname());
////        userEntity.setUserPwd(bCryptPasswordEncoder.encode(userDTO.getUserPwd()));
//        userEntity.setUserPwd(userDTO.getUserPwd());
//        userEntity.setUserPhone(userDTO.getUserPhone());
////        userEntity.setUserAddr(userDTO.getUserAddr());
//        userEntity.setUserPostcode(userDTO.getUserPostcode());
//        userEntity.setUserAddress(userDTO.getUserAddress());
//        userEntity.setUserDetailaddress(userDTO.getUserDetailaddress());
//        userEntity.setUserExtraaddress(userDTO.getUserExtraaddress());
//        userEntity.setRole("ROLE_USER");
        UserEntity userEntity = UserEntity.toUserEntity(userDTO);
        userRepository.save(userEntity);
    }

}
