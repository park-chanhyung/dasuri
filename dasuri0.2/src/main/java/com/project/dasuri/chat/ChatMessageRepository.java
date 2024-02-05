package com.project.dasuri.chat;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatMessageRepository extends MongoRepository<ChatMessageEntity, String> {
    // 추가적인 쿼리 메서드가 필요한 경우 정의 가능

}