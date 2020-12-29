package com.shiro.service;

import com.shiro.common.JsonData;
import com.shiro.model.User;
import com.shiro.param.UserParam;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface UserService {

    User findByUserName(@Param("username") String username);

    void save(UserParam param);

    User findByEmail(String email);

    Map<String,Object> login(User user) throws Exception;

    JsonData tokenRefresh(String refreshToken);

    Map<String, String> refreshToken(String token);
}
