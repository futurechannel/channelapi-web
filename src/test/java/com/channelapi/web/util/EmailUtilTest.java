package com.channelapi.web.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:config/spring/spring-*.xml")
public class EmailUtilTest {

    @Test
    public void sendEmail() {
        String toEmails = "15240025221@163.com,463302373@qq.com";
        EmailUtil.sendEmail("15240025221@163.com", toEmails, "测试", "测试");
    }
}