package com.project.dasuri.member.dto;

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
