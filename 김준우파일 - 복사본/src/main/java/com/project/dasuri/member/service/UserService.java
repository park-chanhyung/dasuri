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

    public void sign_up(UserDTO userDTO){
        System.out.println("UserService.sign_up");
        System.out.println("userDTO = " + userDTO);
        UserEntity userEntity = UserEntity.toUserEntity(userDTO);
        userRepository.save(userEntity);
    }

}
