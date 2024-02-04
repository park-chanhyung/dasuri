package com.project.dasuri.member.controller;

import com.project.dasuri.member.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class SmsController {
    @Autowired
    private SmsService smsService;

    @PostMapping("/sendVerificationCode")
    public ResponseEntity<?> sendVerificationCode(@RequestBody HashMap<String, String> request) {
        String phoneNumber = request.get("phoneNumber");
        String verificationCode = smsService.generateVerificationCode();
        String messageContent = "인증번호는 [" + verificationCode + "] 입니다.";

        boolean success = smsService.sendSMS(phoneNumber, messageContent);
        if (success) {
            return ResponseEntity.ok().body("인증번호가 발송되었습니다.");
        } else {
            return ResponseEntity.badRequest().body("인증번호 발송에 실패하였습니다.");
        }
    }
}
