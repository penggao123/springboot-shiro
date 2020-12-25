package com.shiro.service;

import com.shiro.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserService {

    User findByUserName(@Param("username") String username);
}
