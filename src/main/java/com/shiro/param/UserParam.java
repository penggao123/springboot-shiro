package com.shiro.param;

import com.shiro.model.User;

/**
 * @ClassName UserParam
 * @Description TODO
 * @Author gaopeng
 * @Date 2020/12/26 14:44
 * @Version 1.0
 **/

public class UserParam extends User{
    /**
     * 名称
     */
    private String name;

    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 状态  1：启用  0：禁用
     */
    private Byte status;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Byte getStatus() {
        return status;
    }

    @Override
    public void setStatus(Byte status) {
        this.status = status;
    }
}
