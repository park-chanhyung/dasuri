package com.project.dasuri.shop.service;

import com.project.dasuri.member.entity.UserEntity;
import com.project.dasuri.shop.entity.ReviewEntity;
import com.project.dasuri.shop.entity.ShopEntity;
import com.project.dasuri.shop.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public void create(ShopEntity shopEntity, String comment, UserEntity author,String  star)
    {
        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setComment(comment);
        reviewEntity.setCreateTime(LocalDateTime.now());
        reviewEntity.setShopEntity(shopEntity);
        reviewEntity.setStar(star);
        reviewEntity.setAuthor(author);
        this.reviewRepository.save(reviewEntity);
    }

    // 상점에 대한 리뷰의 평균 별점을 계산하는 메서드
    public Double calculateAverageRating(ShopEntity shopEntity) {
        List<ReviewEntity> reviews = reviewRepository.findByShopEntity(shopEntity);

        if (reviews == null || reviews.isEmpty()) {
            return 0.0; // 리뷰가 없거나 목록이 null인 경우 평균 별점을 0.0으로 반환
        }

        // 리뷰 목록에서 별점을 모두 더합니다.
        double totalRating = reviews.stream()
                .filter(reviewEntity -> reviewEntity.getStar() != null) // Null 값인 리뷰는 건너뜁니다.
                .mapToDouble(reviewEntity -> Double.parseDouble(reviewEntity.getStar()))
                .sum();

        // 리뷰의 평균 별점을 계산합니다.
        double avgRating = totalRating / reviews.size();

        // 소수점 둘째 자리까지 반올림하여 반환합니다.
        return Math.round(avgRating * 100.0) / 100.0;
    }

    public Double starAvg(ShopEntity shopEntity) {
        return reviewRepository.starAvg(shopEntity);
    }
}
