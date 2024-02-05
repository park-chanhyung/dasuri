package com.project.dasuri.shop.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewForm {

    @NotEmpty(message = "리뷰를 적어주세요")
    private String comment;

    @NotEmpty(message = "별점을 입력해주세요")
    private String reviewStar;
}
