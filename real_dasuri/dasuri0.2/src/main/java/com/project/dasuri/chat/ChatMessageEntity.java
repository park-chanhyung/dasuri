package com.project.dasuri.chat;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "chatMessages")
public class ChatMessageEntity {

    @Id
    private String id;
    private String sender;
    private String content;

    // 생성자, getter, setter

    public ChatMessageEntity() {
    }

    public ChatMessageEntity(String sender, String content) {
        this.sender = sender;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    // 다른 getter, setter 및 필요한 메서드
}