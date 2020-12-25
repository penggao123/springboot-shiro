package com.shiro.service;

import java.util.List;

public interface UserRoleService {

    List<Integer> findRoleIdListByUserId(Integer userId);
}
