package com.example.demo11.controller;

import com.example.demo11.service.EmailService;
import com.example.demo11.vo.EmailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class EmailController {
    @Autowired
    private EmailService emailService;


    @PostMapping("/sendEmail")
    public String sendEmail(@RequestBody EmailVo email) {
        String subject = "测试邮件";
        String content = email.getContent();
        String recipientEmail = email.getRecipientEmail();
        emailService.sendEmail(recipientEmail, subject, content);

        return "邮件发送成功！";
    }


}
