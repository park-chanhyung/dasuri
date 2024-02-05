package com.project.dasuri.chat.mysql;

import com.project.dasuri.chat.Message;
import com.project.dasuri.member.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "chat_Msg")
public class MysqlChat extends Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(length = 255)
    private String content;

    @Column
    private int roomNum;

    private LocalDateTime msgTime;


    @ManyToOne
    private UserEntity userEntity;
}
