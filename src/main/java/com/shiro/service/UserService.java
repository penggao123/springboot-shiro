package com.shiro.service;

import com.shiro.model.User;
import com.shiro.param.UserParam;
import org.apache.ibatis.annotations.Param;

public interface UserService {

    User findByUserName(@Param("username") String username);

    void save(UserParam param);

    User findByEmail(String email);
}
