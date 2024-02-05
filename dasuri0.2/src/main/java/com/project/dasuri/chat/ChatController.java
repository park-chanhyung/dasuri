package com.project.dasuri.chat;


import com.project.dasuri.chat.mysql.MysqlChat;
import com.project.dasuri.chat.mysql.MysqlChatRepository;
import com.project.dasuri.chat.mysql.MysqlChatService;
import com.project.dasuri.member.entity.UserEntity;
import com.project.dasuri.member.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class ChatController {
    private final MysqlChatRepository mysqlChatRepository;
    private final SimpMessageSendingOperations messagingTemplate;
    private final ChatMessageRepository chatMessageRepository;
    private final MysqlChatService mysqlChatService;
    private final UserService userService;
    @Transactional
    @MessageMapping("/chat")
    public void sendMessage(@RequestBody Message message, Principal principal) {
        // 받은 메시지를 "/topic/messages"로 모든 연결된 클라이언트에게 전송
        messagingTemplate.convertAndSend("/topic/messages", new Message(message.getSender(), message.getContent(), message.getRoomNum()));

        // MongoDB에 채팅 메시지 저장
        ChatMessageEntity chatMessageEntity = new ChatMessageEntity(message.getSender(), message.getContent());
        chatMessageRepository.save(chatMessageEntity);


        String userId = principal.getName();
        UserEntity userEntity = userService.findByUserId(userId);
        if (userEntity != null) {
            UserEntity user = this.userService.getSiteUser(userEntity.getNum());
            if (user != null) {
                mysqlChatService.saveChatMessage(message.getContent(), message.getRoomNum(), user);
            } else {
                System.out.println("UserEntity for site user is null");
            }
        } else {
            System.out.println("UserEntity for user id is null");
        }
    }

    @GetMapping("/chatroom/{id}")
    public String showChatPage(@RequestParam("roomNum") int roomNum, Model model,Principal principal, @PathVariable("id") int id) {
        // 여기서 roomNum을 사용하여 적절한 처리를 수행하세요.
        // 예를 들어, 채팅방에 대한 정보를 가져오거나 사용자를 해당 채팅방으로 리다이렉트할 수 있습니다.

        String userId = principal.getName();
        UserEntity userEntity = userService.findByUserId(userId);
        List<MysqlChat> chatMessages = mysqlChatRepository.findByRoomNumOrderByMsgTime(roomNum);
        List<MysqlChat> mysqlChatList = this.mysqlChatService.getChatList();
        System.out.println("mysqlChatList#$#@$##$" + mysqlChatList);

        model.addAttribute("username", userEntity);
        model.addAttribute("mysqlChatList", mysqlChatList);
        model.addAttribute("chatMessages", chatMessages);
        model.addAttribute("roomNum", roomNum);
        return "chat";
    }

    @GetMapping("/chatHistory")
    public String chatHistory(Model model,Principal principal){
        String userId = principal.getName();
        UserEntity userEntity = userService.findByUserId(userId);

        // siteUser의 id로 채팅방 번호 목록 조회
        List<Integer> roomNumList = mysqlChatService.findRoomNumBySiteUserId(userEntity.getNum());
        model.addAttribute("roomNumList", roomNumList);
        model.addAttribute("user",userEntity);
        return "chatHistory";
    }

    @PostMapping("/chatHistory/delete")
    public String deleteChatRoom(@RequestParam("roomNum") int roomNum){
        System.out.println("delete문 실행");
        mysqlChatService.deleteByRoomNum(roomNum);
        return "redirect:/index";
    }
}