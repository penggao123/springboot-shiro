package com.shiro.controller;


import com.shiro.common.JsonData;
import com.shiro.common.ResponseCode;
import com.shiro.model.Role;
import com.shiro.param.RoleParam;
import com.shiro.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/role")
@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;


    @RequiresPermissions("/role/list")
    @GetMapping("/list")
    public JsonData lists(){
        List<Role> roleList =  roleService.getLists();
        return new JsonData(true, "查询成功" ,roleList, ResponseCode.OK);
    }
}
