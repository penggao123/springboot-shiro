package com.shiro.config;

import com.shiro.model.User;
import com.shiro.service.UserService;
import com.shiro.utils.TokenUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@Configuration(value = "jwtRealm")
public class JwtRealm extends AuthenticatingRealm {

    @Autowired
    private UserService userService;

    /**
     * 限定这个Realm只支持我们自定义的JWT Token
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 认证方法
     * @param
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        System.out.println(auth);
        String token = (String) auth.getCredentials();
        // 获得phone
        String userName = TokenUtils.getName(token);
        if (StringUtils.isEmpty(userName)) {
            throw new IncorrectCredentialsException();
        }
        User user = userService.findByUserName(userName);
        if (null == user) {
            return null;
        }
        try{
            boolean verify = TokenUtils.verify(token, user.getPassword());
            if(!verify){
                throw new IncorrectCredentialsException();
            }
        } catch (Exception e){
            throw new IncorrectCredentialsException();
        }
        return new SimpleAuthenticationInfo(user, token, "jwtRealm");
    }
}
