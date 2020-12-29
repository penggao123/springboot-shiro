package com.shiro.service.impl;

import com.shiro.common.Constant;
import com.shiro.common.JsonData;
import com.shiro.common.ResponseCode;
import com.shiro.exception.InvalidVoucherException;
import com.shiro.mapper.UserMapper;
import com.shiro.model.User;
import com.shiro.param.UserParam;
import com.shiro.service.UserService;
import com.shiro.utils.EncryptMd5;
import com.shiro.utils.TokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Map<String, Object> login(User user) throws Exception{

        Map<String, Object> hashMap = new LinkedHashMap<>();

        UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), user.getPassword());

        //获取当前登录用户
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        User users = userMapper.findByUserName(user.getName());
//        token.setRememberMe(true);//设置记住我(别忘记securityManager设置)
//            user.getSession().setAttribute("currentUser",user.getPrincipal());
        User userByUserName = userMapper.findByUserName(user.getName());
        // 生成jwtToken
        String password = EncryptMd5.passWordEncode("MD5", user.getPassword(), ByteSource.Util.bytes("salt"), 12);
        String acquireToken = TokenUtils.createToken(user.getName(), users.getId(), password, Constant.TOKEN_EXPIRE_TIME);
        // 生成刷新token
        String refreshToken = TokenUtils.createToken(user.getName(), users.getId(), password, Constant.TOKEN_REFRESH_TIME);

        hashMap.put("token", acquireToken);
        // 刷新时所需token
        hashMap.put("refreshToken", refreshToken);
        return hashMap;
    }

    /**
     * 用户名称查询用户
     * @param username
     * @return
     */
    @Override
    public User findByUserName(String username) {
        return userMapper.findByUserName(username);
    }

    @Override
    public User findByEmail(String email) {
        return userMapper.findByEmail(email);
    }

    /**
      * @Author gaopeng
      * @Description //添加用户
      * @Date 14:47 2020/12/26
      * @param
      * @return void
      **/
    @Override
    public void save(UserParam param) {

        User user = new User();
        user.setCreateTime(new Date());
        user.setName(param.getName());
        user.setEmail(param.getEmail());
        String passWordEncode = EncryptMd5.passWordEncode("MD5", param.getPassword(), ByteSource.Util.bytes("salt"), 12);
        user.setPassword(passWordEncode);
        user.setUpdateTime(new Date());
        int saveResult = userMapper.insertSelective(user);

    }

    @Override
    public JsonData tokenRefresh(String refreshToken) {
        String userName = TokenUtils.getName(refreshToken);
        User user = userMapper.findByUserName(userName);
        boolean verify = TokenUtils.verify(refreshToken, user.getPassword());
        if (!verify) {
            return new JsonData(true, "认证过期,请重新登录", new LinkedHashMap<>(),ResponseCode.TOKEN_ERROR);
        }
        Map<String, Object> resultMap = new HashMap<>(4);
        // 生成jwtToken
        String newToken = TokenUtils.createToken(user.getName(), user.getId(), user.getPassword(), Constant.TOKEN_EXPIRE_TIME);
        // 生成刷新token
        String newRefreshToken = TokenUtils.createToken(user.getName(), user.getId(), user.getPassword(), Constant.TOKEN_REFRESH_TIME);
        // toke
        resultMap.put("token", newToken);
        // 刷新时所需token
        resultMap.put("refreshToken", newRefreshToken);
        return new JsonData(true, "获取成功", resultMap,ResponseCode.OK);
    }

    /**
     * 刷新token
     * @param token
     * @return
     */
    @Override
    public Map<String, String> refreshToken(String token) {
        String name = TokenUtils.getName(token);
        if (StringUtils.isBlank(name)) {
            throw new InvalidVoucherException();
        }
        User user = userMapper.findByUserName(name);
        boolean verify = TokenUtils.verify(token, user.getPassword());
        if (!verify) {
            throw new InvalidVoucherException();
        }
        Map<String, Object> data = new HashMap<>(4);

        String passWordEncode = EncryptMd5.passWordEncode("MD5", user.getPassword(), ByteSource.Util.bytes("salt"), 12);
        // 生成jwtToken
        String newToken = TokenUtils.createToken(user.getName(), user.getId(), passWordEncode, Constant.TOKEN_EXPIRE_TIME);
        // 生成刷新token
        String newRefreshToken = TokenUtils.createToken(user.getName(), user.getId(), passWordEncode, Constant.TOKEN_REFRESH_TIME);
        // toke
        data.put("token", newToken);
        // 刷新时所需token
        data.put("refreshToken", newRefreshToken);
        return null;
    }
}
