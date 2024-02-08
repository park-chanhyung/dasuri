package com.project.dasuri.chat.mysql;

import com.project.dasuri.member.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MysqlChatService {

    @Autowired
    private MysqlChatRepository mysqlChatRepository;

    public void saveChatMessage(String message, int roomNum, UserEntity user) {
        MysqlChat chatMessage = new MysqlChat();
        chatMessage.setContent(message);
        chatMessage.setRoomNum(roomNum);
        chatMessage.setMsgTime(LocalDateTime.now());
        chatMessage.setUserEntity(user);
        mysqlChatRepository.save(chatMessage);
    }
    public void save(MysqlChat mysqlChat) {
        mysqlChatRepository.save(mysqlChat);
    }

    public List<MysqlChat> getChatList() {
        return this.mysqlChatRepository.findAll();
    }

    public List<Integer> findAllRoomNum() {
        return mysqlChatRepository.findAllRoomNum();
    }

    public int findmaxRoomNum() {
        return mysqlChatRepository.findMaxRoomNum();
    }
    // @Query 어노테이션을 사용하여 직접 JPQL 쿼리를 정의
    public List<Integer> findRoomNumBySiteUserId(int userId) {
        return mysqlChatRepository.findRoomNumBySiteUserId((long) userId);
    }
    @Transactional
    public void deleteByRoomNum(int roomNum){
      mysqlChatRepository.deleteByRoomNum(roomNum);
    }


}