package com.shiro.mapper;

import com.shiro.model.User;
import org.springframework.stereotype.Repository;


public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User findByUserName(String username);

    User findByEmail(String email);
}