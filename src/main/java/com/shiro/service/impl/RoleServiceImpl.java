package com.shiro.service.impl;

import com.shiro.mapper.RoleMapper;
import com.shiro.model.Role;
import com.shiro.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * id集合查询对象list
     * @param roleIdList
     * @return
     */@Override
    public List<Role> findByIdList(List<Integer> roleIdList) {
        List<Role> roleList = roleMapper.findByIdList(roleIdList);
        return roleList;
    }

    @Override
    public List<Role> getLists() {
        return roleMapper.getLists();
    }
}
