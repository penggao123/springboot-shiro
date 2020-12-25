package com.shiro.service;

import com.shiro.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> findByIdList(List<Integer> roleIdList);

    List<Role> getLists();
}
