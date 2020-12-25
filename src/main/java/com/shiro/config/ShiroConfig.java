package com.shiro.config;

import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    /**
     * 配置shiro三大点
     * 1、realm
     * 2、securityManager
     * 3、请求过滤器
     */

    public Realm myRealm() {
        return new MyRealm();
    }

    /**
     * 流程控制
     * @return
     */
    @Bean
    public DefaultWebSecurityManager mySecurityManager(Realm myRealm) {

        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm);
        return securityManager;
    }


    /**
     * 请求过滤器
     */
    @Bean
    public ShiroFilterFactoryBean  getShiroFilterFactoryBean(DefaultSecurityManager mySecurityManager) {

        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();

        factoryBean.setSecurityManager(mySecurityManager);
        factoryBean.setLoginUrl("/common/unauthc");//登录地址
//        factoryBean.setUnauthorizedUrl(); //未经授权页
        //shiro认证的；类为DefaultFilter
        Map<String, String> filter = new HashMap<>();
        filter.put("/common/getUserInfo", "authc");//需要登录
//        filter.put("/common/logout", "logout");//退出 和subject.logout();一样的效果
        filter.put("/common/login", "anon");
        filter.put("/common/unauthc", "anon");

        factoryBean.setFilterChainDefinitionMap(filter);
        return factoryBean;
    }
}
