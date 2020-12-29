package com.shiro.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AllSuccessfulStrategy;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.FirstSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
    public DefaultWebSecurityManager mySecurityManager(AuthorizingRealm myRealm, AuthenticatingRealm jwtRealm) {

        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

//------------------------密码加密------------------------
        //将密码加密过程放在realm中进行,盐值需要在doGetAuthenticationInfo（认证方法中进行）
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("MD5");//加密方案
        matcher.setHashIterations(12);//加密散列次数
        myRealm.setCredentialsMatcher(matcher);//用户名认证realm
//        emailRealm.setCredentialsMatcher(matcher);//邮箱认证realm

//        -----------------------realm认证----------------------------
        securityManager.setRealms(Arrays.asList(myRealm, jwtRealm));
//        securityManager.setRealm(myRealm);

//        -----------------------多realm认证策略(默认：)-------------------------------
        /**
          * AllSuccessfulStrategy:多个realm都认证通过才能认证成功
          * AtLeastOneSuccessfulStrategy(默认)：多个realm有一个认证通过就能认证成功
          * FirstSuccessfulStrategy：多个realm中，第一个realm成功才能认证成功
          *
          **/
//        ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
//        authenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
//        securityManager.setAuthenticator(authenticator);

        //--------------------设置记住我时长---------------
//        CookieRememberMeManager meManager = new CookieRememberMeManager();
//        Cookie cookie = new SimpleCookie("rememberMe");
//        cookie.setHttpOnly(true);
//        cookie.setMaxAge(31536000);
//        meManager.setCookie(cookie);
//        securityManager.setRememberMeManager(meManager);

//        ---------------认证缓存----------------------
//        MemoryConstrainedCacheManager memoryConstrainedCacheManager = new MemoryConstrainedCacheManager();
//        securityManager.setCacheManager(memoryConstrainedCacheManager);


//      ---------------------------------关闭shiro的session-------------------------------------
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);
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
        Map<String, String> filterMap = new HashMap<>();
//        filterMap .put("/common/getUserInfo", "authc");//需要登录
//        filter.put("/common/logout", "logout");//退出 和subject.logout();一样的效果
//        filterMap.put("/common/login", "anon");
        filterMap.put("/common/unauthc", "anon");

        Map<String, Filter> filter = new LinkedHashMap<>();
        filter.put("jwt", new JwtFilter());
        factoryBean.setFilters(filter);
        filterMap.put("/**", "jwt");
        factoryBean.setFilterChainDefinitionMap(filterMap);
        return factoryBean;
    }
}
