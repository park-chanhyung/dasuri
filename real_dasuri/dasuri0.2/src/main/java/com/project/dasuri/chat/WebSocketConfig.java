package com.project.dasuri.chat;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker //메시지 브로커가 지원하는 WebSocket 메시지 처리를 활성화
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    // WebSocket 메시지 브로커를 설정하는 메서드(MessageBrokerRegistry)를 오버라이드합니다.
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // "/topic"으로 시작하는 메시지를 구독하는 브로커를 활성화합니다.
        config.enableSimpleBroker("/topic");
        // SockJS 없이 작동하는 끝점도 추가
//접두사가 "/app"인 이 끝점은 ChatController.send() 메서드가 핸들에 매핑 되는 끝점
        config.setApplicationDestinationPrefixes("/app");
    }

    // Stomp 웹 소켓 엔드포인트를 등록하는 메서드(StompEndpointRegistry)를 오버라이드합니다.
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // "/chat" 엔드포인트를 추가하고, SockJS를 사용하여 WebSocket 연결을 지원합니다.
        registry.addEndpoint("/chat").withSockJS();
    }
}