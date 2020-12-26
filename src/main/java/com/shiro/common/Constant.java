package com.shiro.common;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName Constant
 * @Description TODO
 * @Author gaopeng
 * @Date 2020/12/26 21:28
 * @Version 1.0
 **/

public class Constant {


    /**
     * jwtToken过期时间 20分钟
     */
    public static Long TOKEN_EXPIRE_TIME = TimeUnit.MINUTES.toMillis(20);

    /**
     * jwtToken刷新时间　7天
     */
    public static Long TOKEN_REFRESH_TIME = TimeUnit.DAYS.toMillis(7);

    /**
     * token请求头名称
     */
    public static String TOKEN_HEADER_NAME = "Authorization";
}
