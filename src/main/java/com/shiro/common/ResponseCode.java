package com.shiro.common;

import org.springframework.http.HttpStatus;

public class ResponseCode {

    public static final Integer OK = 200;//正确

    public static final Integer UNAUTHORIZED = 401;//未登录

    public static final Integer UNKNOWN_ACCOUNT = 402;//用户不存在

    public static final Integer PASSWORD_WORD_ERROR = 405;//密码错误

    public static final Integer ACCOUNT_LOCK = 406;//用户已锁定

    public static final Integer FORBIDDEN = 403;//没有权限

    public static final Integer SYSTEM_ERROR = 500;//服务器异常

    public static final Integer TOKEN_ERROR = 601;//token失效


}
