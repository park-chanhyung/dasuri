package com.project.dasuri.member.dto;

import com.project.dasuri.member.entity.ProEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProDTO {
    private String proId;
    private String proName;
    private String proNickname;
    private String proPwd;
    private String proPhone;
    private String proLegions;


    public static ProDTO toProDTO(ProEntity proEntity){
        ProDTO proDTO = new ProDTO();
        proDTO.setProId(proEntity.getProId());
        proDTO.setProName(proEntity.getProName());
        proDTO.setProNickname(proEntity.getProNickname());
        proDTO.setProPwd(proEntity.getProPwd());
        proDTO.setProPhone(proEntity.getProPhone());
        proDTO.setProLegions(proEntity.getProLegions());

        return proDTO;
    }
}
