package com.project.dasuri.mypage.dto;


import com.project.dasuri.mypage.entity.UserPageEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserPageDTO {
    private Long id;
    private MultipartFile file; //첨부파일
    private String filename; //첨부파일명(uuid)
    private String filePath;//첨부파일경로

    public static UserPageDTO toUserPageDTO(UserPageEntity userPageEntity){
        UserPageDTO userPageDTO = new UserPageDTO();
        userPageDTO.setFilename(userPageEntity.getFilename());//파일명
        userPageDTO.setFilePath(userPageEntity.getFilePath());//파일 경로
        return userPageDTO;
    }
}
