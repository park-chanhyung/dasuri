package com.project.dasuri.member.service;

import com.project.dasuri.member.dto.UserDTO;
import com.project.dasuri.member.entity.UserEntity;
import com.project.dasuri.member.repository.ProRepository;
import com.project.dasuri.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    //repository 호출
    private final UserRepository userRepository;
    private final ProRepository proRepository;

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

        // user_table에서 아이디 검사
        boolean isDuplicateInUserTable = userRepository.existsByUserId(userId);
        // pro_table에서 아이디 검사
        boolean isDuplicateInProTable = proRepository.existsByProId(userId);

        return isDuplicateInUserTable || isDuplicateInProTable;
    }

    public boolean isUserNicknameDuplicate(String userNickname) {
        // user_table에서 닉네임 검사
        boolean isNicknameDuplicateInUserTable = userRepository.existsByUserNickname(userNickname);
        // pro_table에서 닉네임 검사
        boolean isNicknameDuplicateInProTable = proRepository.existsByProNickname(userNickname);

        return isNicknameDuplicateInUserTable || isNicknameDuplicateInProTable;
    }
}
