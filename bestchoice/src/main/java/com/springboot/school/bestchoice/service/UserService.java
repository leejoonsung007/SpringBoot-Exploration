package com.springboot.school.bestchoice.service;

import com.google.common.collect.Lists;
import com.springboot.school.bestchoice.mapper.UserMapper;
import com.springboot.school.bestchoice.model.User;
import com.springboot.school.bestchoice.utils.BeanHelper;
import com.springboot.school.bestchoice.utils.HashUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private MailService mailService;

    @Autowired
    private FileService fileService;

    @Autowired
    private UserMapper userMapper;

    @Value("${file.prefix}")
    private String imgPrefix;

    @Transactional(rollbackFor = Exception.class)
    public boolean addUser(User user){
        user.setPassword(HashUtils.encrypt(user.getPassword()));
//        List<String> imgList = fileService.getImgPaths(Lists.newArrayList(user.getAvatarFile()));
//        if(imgList.isEmpty()){
//            user.setPhoto(imgList.get(0));
//        }
        BeanHelper.setDefaultProp(user, User.class);
        BeanHelper.onInsert(user);
        user.setConfirmed(0);
        user.setRole_id(1);
        userMapper.insert(user);
        mailService.registerNotification(user.getEmail());
        return true;
    }

    public boolean enable(String key){
        return mailService.enable(key);
    }

    //check the account
    public User check(String username, String password){
        User user = new User();
        user.setEmail(username);
        user.setPassword(HashUtils.encrypt(password));
        user.setConfirmed(1);
        user.setLogin_type("Website");
        List<User> list = getUserByQuery(user);
        if(!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

    public List<User> getUserByQuery(User user){
        List<User> list = userMapper.selectUsersByQuery(user);
        return list;
    }
}
