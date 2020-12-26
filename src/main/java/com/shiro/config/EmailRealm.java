package com.shiro.config;

import com.shiro.model.User;
import com.shiro.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName EmailRealm
 * @Description TODO
 * @Author gaopeng
 * @Date 2020/12/26 15:51
 * @Version 1.0
 **/

@Configuration(value = "emailRealm")
public class EmailRealm extends AuthenticatingRealm {

    @Autowired
    private UserService userService;


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String email = token.getUsername();
        //email查询用户
        User user = userService.findByEmail(email);
        if (null == user) {
            return null;
        }


        return new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes("salt"), "emailRealm");
    }

}
