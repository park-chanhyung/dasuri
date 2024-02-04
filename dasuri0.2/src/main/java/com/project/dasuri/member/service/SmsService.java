package com.project.dasuri.member.service;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class SmsService {
    private final DefaultMessageService messageService;
//    private String apiKey;
//    private String apiSecret;
//    private String fromPhoneNumber;
//
//    public SmsService(@Value("${coolsms.api.key}") String apiKey,
//                      @Value("${coolsms.api.secret}") String apiSecret,
//                      @Value("${coolsms.from.phone.number}") String fromPhoneNumber) {
//        this.messageService = NurigoApp.INSTANCE.initialize(apiKey, apiSecret, "https://api.coolsms.co.kr");
//        // 추가적인 초기화 로직...
//    }
//==============================================//
//    @Value("${coolsms.api.key}")
//    private String apiKey;
//
//    @Value("${coolsms.api.secret}")
//    private String apiSecret;
//
//    @Value("${coolsms.from.phone.number}")
//    private String fromPhoneNumber;

    public SmsService() {
        this.messageService = NurigoApp.INSTANCE.initialize("NCSLHJCMLPN7HUZH", "HB01OYNOP2UZ7YZ2EVM3517RBXHCWDX5", "http://api.coolsms.co.kr");
    }

    // 인증번호 생성
    public String generateVerificationCode() {
        int code = (int) (Math.random() * 1000000);
        return String.format("%06d", code); // 6자리 숫자로 포맷
    }

    // SMS 발송
    public boolean sendSMS(String toPhoneNumber, String content) {
        Message message = new Message();
        message.setFrom("01064376348");
        message.setTo(toPhoneNumber);
        message.setText(content);

        try {
            SingleMessageSendingRequest request = new SingleMessageSendingRequest(message);
            SingleMessageSentResponse response = this.messageService.sendOne(request);
            System.out.println(response); // 성공 응답 로그 출력
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
