package com.shiro.service;

import com.shiro.model.Permission;

import java.util.List;

public interface PermissionService {

    List<Permission> findByIdList(List<Integer> permissionIdList);
}
