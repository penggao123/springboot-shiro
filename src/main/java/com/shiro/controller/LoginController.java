package com.shiro.controller;


import com.shiro.common.JsonData;
import com.shiro.common.ResponseCode;
import com.shiro.model.User;
import com.shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/common")
@RestController
public class LoginController {


    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public JsonData login(@RequestBody User user) {

        JsonData jsonData = null;
        Map<String, Object> resultMap = null;

        try{
            resultMap = userService.login(user);
            jsonData = new JsonData(true, "登录成功", resultMap,ResponseCode.OK);
        } catch (UnknownAccountException uae) {
            jsonData = new JsonData(true, "用户不存在", resultMap,ResponseCode.UNKNOWN_ACCOUNT);
        } catch (IncorrectCredentialsException ice) {
            jsonData = new JsonData(true, "密码不正确", resultMap,ResponseCode.PASSWORD_WORD_ERROR);
        } catch (LockedAccountException lae) {
            jsonData = new JsonData(true, "账号被锁定", resultMap,ResponseCode.ACCOUNT_LOCK);
        } catch(AuthenticationException authe){
            jsonData = new JsonData(true, "登录失败", resultMap,ResponseCode.SYSTEM_ERROR);
        } catch (Exception e){
            jsonData = new JsonData(true, "登录失败", resultMap,ResponseCode.SYSTEM_ERROR);
        }
        return jsonData;
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

    @PostMapping("/token/refresh")
    public JsonData tokenRefresh(@RequestParam String refreshToken){
        return userService.tokenRefresh(refreshToken);
    }


}
