package com.project.dasuri.member.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
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
    private String proPwd;
    private String proPhone;
    private String proLegions;
}
