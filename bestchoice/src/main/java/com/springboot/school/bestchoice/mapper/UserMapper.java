package com.springboot.school.bestchoice.mapper;

import com.springboot.school.bestchoice.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {

    public int insert(User account);

    public int delete(String email);

    public int update(User updateUser);

    public List<User> selectUsersByQuery(User user);
}
