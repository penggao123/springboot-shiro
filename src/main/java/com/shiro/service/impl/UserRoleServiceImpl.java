package com.shiro.service.impl;

import com.shiro.mapper.UserRoleMapper;
import com.shiro.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     * 用户id获取角色id集合
     * @param userId
     * @return
     */
    @Override
    public List<Integer> findRoleIdListByUserId(Integer userId) {
        return userRoleMapper.findRoleIdListByUserId(userId);
    }
}
