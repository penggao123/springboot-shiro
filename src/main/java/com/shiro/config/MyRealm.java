package com.shiro.config;

import com.google.common.collect.Lists;
import com.shiro.mapper.PermissionMapper;
import com.shiro.model.Permission;
import com.shiro.model.Role;
import com.shiro.model.User;
import com.shiro.model.UserRole;
import com.shiro.service.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
  * @Author gaopeng
 *
 * AuthorizingRealm是用于做授权的
 * AuthenticatingRealm用于做认证
 * AuthorizingRealm继承AuthenticatingRealm
 *
  **/
@Configuration(value = "myRealm")
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService ;


    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //获取当前用户名
        User user = (User) principals.getPrimaryPrincipal();
        //名称查询当前用户
//        User user = userService.findByUserName(userName);
        //获取当前用户角色
        List<Integer> roleIdList =  userRoleService.findRoleIdListByUserId(user.getId());
        if (CollectionUtils.isNotEmpty(roleIdList)) {
            //角色查询对应的
            List<Role> roleList = roleService.findByIdList(roleIdList);
            if (CollectionUtils.isNotEmpty(roleIdList)) {
                for (Role role: roleList) {
                    //添加shiro中
                    authorizationInfo.addRole(role.getGuardName());
                    //角色id查询权限id集合
                    List<Integer> permissionIdList = rolePermissionService.findPermissionIdListByRoleId(role.getId());
                    if (CollectionUtils.isNotEmpty(permissionIdList)) {
                        //权限id集合查询对应的权限集合
                        List<Permission> permissionList = permissionService.findByIdList(permissionIdList);
                        if (CollectionUtils.isNotEmpty(permissionList)) {
                            //添加到shiro权限中
                            for (Permission permission :permissionList) {
                                authorizationInfo.addStringPermission(permission.getUrl());
                            }
                        }

                    }

                }

            }

        }

        return authorizationInfo;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //AuthenticationToken 是一个接口实现类中有UsernamePasswordToken，登录时new UsernamePasswordToken(user.getName(), user.getPassword()) 是一个实现类
        // ，所以直接强转就可以得到用户信息
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;

        String username = token.getUsername();
        //根据用户查询用户信息
        User user = userService.findByUserName(username);
        if (user == null) {
            //用户不存在
            return null;
        }
        return new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes("salt"), "myRealm");//密码不进行加密
    }
}
