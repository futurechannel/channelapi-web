package com.channelapi.web.util;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
public class EmailUtil {

    private static EmailUtil emailUtil;

    @PostConstruct
    public void init() {
        emailUtil = this;
    }

    @Resource
    private JavaMailSender javaMailSender;

    public static void sendEmail(String from, String[] to, String subject, String content){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        emailUtil.javaMailSender.send(message);
    }
}
