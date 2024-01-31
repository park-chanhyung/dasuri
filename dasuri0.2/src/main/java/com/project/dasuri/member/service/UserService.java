package com.project.dasuri.member.service;

import com.project.dasuri.member.dto.UserDTO;
import com.project.dasuri.member.entity.UserEntity;
import com.project.dasuri.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    //repository 호출
    private final UserRepository userRepository;

    public void user_signup(UserDTO userDTO){
//        System.out.println("UserService.sign_up");
//        System.out.println("userDTO = " + userDTO);
        UserEntity userEntity = UserEntity.toUserEntity(userDTO);
        userRepository.save(userEntity);
    }

    //로그인 테스트중 gptchat
//    public UserEntity mappingId(String username) {
//        return userRepository.findByUserId(username);
//    }

    public boolean isUserIdDuplicate(String userId) {
        // userId가 이미 존재하는지 여부를 검사하여 반환
        return userRepository.existsByUserId(userId);
    }
}
