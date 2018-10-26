package com.springboot.school.bestchoice.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.springboot.school.bestchoice.mapper.UserMapper;
import com.springboot.school.bestchoice.model.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserMapper userMapper;

    @Value("${domain.name}")
    private String domainName;

    @Value("{spring.mail.username}")
    private String sender;

    private final Cache<String, String> registerCache = CacheBuilder.newBuilder().maximumSize(100).
            expireAfterAccess(15, TimeUnit.MINUTES).removalListener(new RemovalListener<String, String>() {
        @Override
        public void onRemoval(RemovalNotification<String, String> notification) {
            userMapper.delete(notification.getValue());
        }
    }).build();

    public void sendMail(String title, String context, String recipient){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(recipient);
        message.setText("please activate your accounts by clicking the link: " + context);
        message.setSubject(title);
        mailSender.send(message);
    }

    @Async
    public void registerNotification(String email){
        String randomKey = RandomStringUtils.randomAlphanumeric(10);
        registerCache.put(randomKey, email);
        String url = "http://" + domainName + "/accounts/verify?key=" + randomKey;
        sendMail("Account Activation", url, email);
    }

    public boolean enable(String key){
        String email = registerCache.getIfPresent(key);
        if(StringUtils.isBlank(email)){
            return false;
        }
        User updateUser = new User();
        updateUser.setEmail(email);
        updateUser.setConfirmed(1);
        userMapper.update(updateUser);
        registerCache.invalidate(key);
        return true;
    }
}
