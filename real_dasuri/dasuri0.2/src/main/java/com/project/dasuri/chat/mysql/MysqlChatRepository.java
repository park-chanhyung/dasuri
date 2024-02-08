package com.project.dasuri.chat.mysql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MysqlChatRepository extends JpaRepository<MysqlChat, Long> {
    // 추가적인 쿼리 메소드나 기능이 필요하면 여기에 추가
    List<MysqlChat> findByRoomNumOrderByMsgTime(int roomNum);

    @Query("SELECT MAX(m.roomNum) FROM MysqlChat m")
    Integer findMaxRoomNum();


    @Query("SELECT m.roomNum FROM MysqlChat m")
    List<Integer> findAllRoomNum();

//    @Query("SELECT m.roomNum FROM MysqlChat m WHERE m.siteUser.id = :userId")
//    List<Integer> findBySiteUserId(Long userId);

    @Query("SELECT DISTINCT c.roomNum FROM MysqlChat c WHERE c.userEntity.num = :userId")
    List<Integer> findRoomNumBySiteUserId(@Param("userId") Long userId);



    @Modifying
    @Query("DELETE FROM MysqlChat c WHERE c.roomNum = :roomNum")
    void deleteByRoomNum(@Param("roomNum") int roomNum);

}