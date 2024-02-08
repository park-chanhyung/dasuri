package com.project.dasuri.member.dto;

import com.project.dasuri.member.entity.ProEntity;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.parameters.P;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProDTO {
    @NotBlank(message = "아이디를 입력해주세요.")
    @Pattern(regexp = "^[a-z0-9]{4,12}$", message = "영문 소문자/숫자 4~12자리여야 합니다.")
    private String proId;

    @NotBlank(message = "이름을 입력해주세요.")
    private String proName;

    @NotBlank(message = "생년월일을 입력해주세요.")
    @Pattern(regexp = "^(19|20)\\d{6}$",message = "생년월일은 숫자 8자리로 입력해주세요(19990101)")
    private String birth;


    @NotBlank(message = "닉네임을 입력해주세요.")
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "닉네임은 특수문자를 제외한 2~10자리여야 합니다.")
    private String proNickname;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대/소문자, 숫자, 특수문자를 사용하세요.")
    private String proPwd;

    @NotBlank(message = "휴대폰번호를 입력해주세요.")
    @Pattern(regexp = "^01[0-9]{8,9}$", message = "형식에 맞지 않는 번호입니다.")
    private String proPhone;

    @NotBlank(message = "활동 지역구를 선택해주세요.")
    private String proLegions;

    private LocalDateTime signupDate;
    private LocalDateTime lastModifiedDate;
    private LocalDateTime suspensionExpiry;

    //이미지 파일
    private String filename;
    private String filePath;
    private String profileImagePath;

    private int num;

    public ProDTO(String proId, String proName, String proNickname, String proLegions, LocalDateTime signupDate, LocalDateTime suspensionExpiry) {
        this.proId = proId;
        this.proName = proName;
        this.proNickname = proNickname;
        this.proLegions = proLegions;
        this.signupDate = signupDate;
        this.suspensionExpiry = suspensionExpiry;
    }

    public static ProDTO toProDTO(ProEntity proEntity){
        ProDTO proDTO = new ProDTO();
        proDTO.setNum(proEntity.getNum());
        proDTO.setProId(proEntity.getProId());
        proDTO.setProName(proEntity.getProName());
        proDTO.setProNickname(proEntity.getProNickname());
        proDTO.setProPwd(proEntity.getProPwd());
        proDTO.setProPhone(proEntity.getProPhone());

//        강서_구 -> 강서구로 변경
        String proLegions = proEntity.getProLegions();
        proLegions = proLegions.replace("_", "");
        proDTO.setProLegions(proLegions);

        proDTO.setBirth(proEntity.getBirth());

        proDTO.setSignupDate(proEntity.getSignupDate());
        proDTO.setLastModifiedDate(proEntity.getLastModifiedDate());
        proDTO.setSuspensionExpiry(proEntity.getSuspensionExpiry());
        //파일
        proDTO.setFilename(proEntity.getFilename());
        proDTO.setFilePath(proEntity.getFilePath());
        proDTO.setProfileImagePath(proEntity.getProfileImagePath());

        return proDTO;
    }


}
