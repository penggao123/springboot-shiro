package com.shiro.controller;


import com.shiro.common.JsonData;
import com.shiro.common.ResponseCode;
import com.shiro.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/common")
@RestController
public class LoginController {


    @PostMapping("/login")
    public Object login(@RequestBody User user) {

        Map<String, String> errorCode = new HashMap<>();

        UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), user.getPassword());

        //获取当前登录用户
        Subject subject = SecurityUtils.getSubject();
        try{
            subject.login(token);
//            user.getSession().setAttribute("currentUser",user.getPrincipal());
            return "login succeed";
        } catch (UnknownAccountException uae) {
            errorCode.put("errorMsg","不存在的用户名");
        } catch (IncorrectCredentialsException ice) {
            errorCode.put("errorMsg","密码不正确");
        } catch (LockedAccountException lae) {
            errorCode.put("errorMsg","账号被锁定");
        } catch(AuthenticationException authe){
            errorCode.put("errorMsg",authe.getMessage());
        }
        return errorCode;
    }


    /**
     *
     * @return
     */
    @GetMapping("/getUserInfo")
    public User getUserInfo() {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        return user;
    }


    /**
     * 需要登录
     * @return
     */
    @RequestMapping("/unauthc")
    @ResponseBody
    public JsonData unauthc() {
        return new JsonData(true, "请先登录", ResponseCode.UNAUTHORIZED);
    }

    /**
     * 退出
     * @return
     */
    @RequestMapping("/logout")
    @ResponseBody
    public JsonData logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new JsonData(true, "退出成功", ResponseCode.OK);
    }


}
