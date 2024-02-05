package com.project.dasuri.member.service;

import com.project.dasuri.member.dto.UserDTO;
import com.project.dasuri.member.entity.ProEntity;
import com.project.dasuri.member.entity.UserEntity;
import com.project.dasuri.member.repository.ProRepository;
import com.project.dasuri.member.repository.UserRepository;
import com.project.dasuri.shop.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public List<String> findUserIdsByUserNameAndBirth(String name, String birth) {
        // Fetch all user entities with the given name and birthdate
        List<UserEntity> userEntities = userRepository.findByUserNameAndBirth(name, birth);

        // Fetch all pro entities with the given name and birthdate
        List<ProEntity> proEntities = proRepository.findByProNameAndBirth(name, birth);

        // Process user entities and mask userId
        Stream<String> userIdsFromUsers = userEntities.stream()
                .map(user -> maskUserId(user.getUserId()));

        // Process pro entities and mask userId
        Stream<String> userIdsFromPros = proEntities.stream()
                .map(pro -> maskUserId(pro.getUserId()));

        // Combine both streams and collect them into a single list
        List<String> combinedUserIds = Stream.concat(userIdsFromUsers, userIdsFromPros)
                .collect(Collectors.toList());


        return combinedUserIds;
    }

    private String maskUserId(String userId) {
        int length = userId.length();

        if (length >= 6) {
            return userId.substring(0, length - 3) + "***";
        } else {
            return userId.substring(0, length - 2) + "**";
        }
    }

    public UserEntity getUser(String userId) {
        UserEntity userEntity = this.userRepository.findByUserId(userId);
        if (userEntity != null) {
            return userEntity;
        } else {
            throw new DataNotFoundException("UserEntity not found");
        }
    }
}
