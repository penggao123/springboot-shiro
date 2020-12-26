package com.shiro.service.impl;

import com.shiro.mapper.UserMapper;
import com.shiro.model.User;
import com.shiro.param.UserParam;
import com.shiro.service.UserService;
import com.shiro.utils.EncryptMd5;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

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


}
