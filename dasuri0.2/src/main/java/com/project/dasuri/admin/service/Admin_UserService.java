package com.project.dasuri.admin.service;

import com.project.dasuri.admin.repository.AdminUserRepository;
import com.project.dasuri.member.dto.UserDTO;
import com.project.dasuri.member.entity.UserEntity;
import com.project.dasuri.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class Admin_UserService {

    private final AdminUserRepository userRepository;

    //    고객리스트
    public List<UserDTO> findAll(){
        List<UserEntity> userEntities = userRepository.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        for (UserEntity userEntity : userEntities){
            UserDTO userDTO = UserDTO.toUserDTO(userEntity);
            userDTO.setRole("고객");
            userDTOS.add(userDTO);
        }
        return userDTOS;
    }

    //    고객 정보 조회
    public UserDTO findByUserId(String userId){
        UserDTO userDTO = UserDTO.toUserDTO(userRepository.findByUserId(userId));
        return userDTO;
    }

}
