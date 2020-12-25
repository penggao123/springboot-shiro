package com.shiro.service.impl;

import com.shiro.mapper.PermissionMapper;
import com.shiro.model.Permission;
import com.shiro.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {


    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> findByIdList(List<Integer> permissionIdList) {
        return permissionMapper.findByIdList(permissionIdList);

    }
}
