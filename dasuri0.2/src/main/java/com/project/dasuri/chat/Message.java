package com.project.dasuri.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    private String sender;
    private String content;
    private int roomNum;


    




    // 기본 생성자 추가
//    public Message() {
//    }
//
//    public Message(String sender, String content, int roomNum) {
//        this.sender = sender;
//        this.content = content;
//        this.roomNum = roomNum;
//    }
//
//    public String getSender() {
//        return sender;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public int getRoomNum() {
//        return roomNum;
//    }
//
//    // 추가된 메서드
//    public void setRoomNum(int roomNum) {
//        this.roomNum = roomNum;
//    }
}