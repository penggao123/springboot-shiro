package com.shiro.service;

import java.util.List;

public interface RolePermissionService {

    List<Integer> findPermissionIdListByRoleId(Integer permissionId);
}
