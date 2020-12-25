package com.shiro.mapper;

import com.shiro.model.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    List<Permission> findByIdList(List<Integer> permissionIdList);
}