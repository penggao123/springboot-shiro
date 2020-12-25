package com.shiro.service.impl;

import com.shiro.mapper.RolePermissionMapper;
import com.shiro.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    /**
     * 角色id查询权限id集合
     * @param permissionId
     * @return
     */
    @Override
    public List<Integer> findPermissionIdListByRoleId(Integer roleId) {
        return rolePermissionMapper.findPermissionIdListByRoleId(roleId);
    }
}
