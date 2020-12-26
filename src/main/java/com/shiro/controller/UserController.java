package com.shiro.controller;

import com.shiro.common.JsonData;
import com.shiro.common.ResponseCode;
import com.shiro.param.UserParam;
import com.shiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
      * @Author gaopeng
      * @Description //添加用户
      * @Date 14:44 2020/12/26
      * @param
      * @return com.shiro.common.JsonData
      **/
    public JsonData save(@RequestBody UserParam param){
        userService.save(param);
        return new JsonData(true, "添加成功", ResponseCode.OK);
    }



}
