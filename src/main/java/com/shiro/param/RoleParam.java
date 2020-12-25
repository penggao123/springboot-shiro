package com.shiro.param;

import com.shiro.model.Role;

public class RoleParam extends Role {

    /**
     * 名称
     */
    private String name;

    private String guardName;

    /**
     * 描述
     */
    private String desc;

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
    public String getGuardName() {
        return guardName;
    }

    @Override
    public void setGuardName(String guardName) {
        this.guardName = guardName;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public void setDesc(String desc) {
        this.desc = desc;
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
