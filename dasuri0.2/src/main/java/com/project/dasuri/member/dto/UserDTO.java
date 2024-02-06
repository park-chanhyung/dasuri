package com.project.dasuri.member.dto;

import com.project.dasuri.member.entity.UserEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDTO {

    @NotBlank(message = "아이디를 입력해주세요.")
    @Pattern(regexp = "^[a-z0-9]{4,12}$", message = "영문 소문자/숫자 4~12자리여야 합니다.")
    private String userId;

    @NotBlank(message = "이름을 입력해주세요.")
    private String userName;

    @NotBlank(message = "생년월일을 입력해주세요.")
    @Pattern(regexp = "^(19|20)\\d{6}$",message = "생년월일은 숫자 8자리로 입력해주세요(19990101)")
    private String birth;

    @NotBlank(message = "닉네임을 입력해주세요.")
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "닉네임은 특수문자를 제외한 2~10자리여야 합니다.")
    private String userNickname;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대/소문자, 숫자, 특수문자를 사용하세요.")
    private String userPwd;


    @NotBlank(message = "휴대폰번호를 입력해주세요.")
    @Pattern(regexp = "^01[0-9]{8,9}$", message = "형식에 맞지 않는 번호입니다.")
    private String userPhone;
//    private UserAddress userAddr;
    @NotBlank(message = "우편번호를 입력해주세요.")
    private String userPostcode;
    private String userAddress;
    @NotBlank(message = "상세주소를 입력해주세요.")
    private String userDetailaddress;
    private String userExtraaddress;
    private String role;

    private LocalDateTime signupDate;
    private LocalDateTime lastModifiedDate;
    private LocalDateTime suspensionExpiry;

    //이미지 파일
    private String filename;
    private String filePath;
    private String profileImagePath;


    public UserDTO(String userId, String userName, String userNickname, String userAddress, String role, LocalDateTime signupDate, LocalDateTime suspensionExpiry) {
        this.userId = userId;
        this.userName = userName;
        this.userNickname = userNickname;
        this.userAddress = userAddress;
        this.role = role;
        this.signupDate = signupDate;
        this.suspensionExpiry = suspensionExpiry;
    }

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
        userDTO.setBirth(userEntity.getBirth());

        userDTO.setSignupDate(userEntity.getSignupDate());
        userDTO.setLastModifiedDate(userEntity.getLastModifiedDate());
        userDTO.setSuspensionExpiry(userEntity.getSuspensionExpiry());

        //파일
        userDTO.setFilename(userEntity.getFilename());
        userDTO.setFilePath(userEntity.getFilePath());
        userDTO.setProfileImagePath(userEntity.getProfileImagePath());

        return userDTO;
    }
}
