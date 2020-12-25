package com.shiro.mapper;

import com.shiro.model.UserPermission;
import org.springframework.stereotype.Repository;


public interface UserPermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserPermission record);

    int insertSelective(UserPermission record);

    UserPermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserPermission record);

    int updateByPrimaryKey(UserPermission record);
}