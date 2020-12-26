package com.shiro.utils;

/**
 * @ClassName TokenUtils
 * @Description TODO
 * @Author gaopeng
 * @Date 2020/12/26 21:19
 * @Version 1.0
 **/

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.shiro.common.Constant;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
  * @Author gaopeng
  * @Description //创建token、校验token
  * @Date 21:20 2020/12/26
  * @param
  * @return
  **/
public class TokenUtils {



    /**
     * 获得token中的信息无需secret解密也能获得
     * @param token token
     * @return token中包含的用户手机号
     */
    public static String getPhone(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("phone").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     * @param token token
     * @return token中包含的用户id
     */
    public static String getUserId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userId").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获取当前登录用户的token,如果token为null则获取refreshToken
     * @param request HttpServletRequest
     * @return token
     */
    public static String getToken(HttpServletRequest request){
        String token = request.getHeader(Constant.TOKEN_HEADER_NAME);
        if (StringUtils.isEmpty(token)) {
            return request.getParameter("refreshToken");
        } else {
            return token;
        }
    }

    /**
     *
     * @param phone 用户名/手机号
     * @param userId   用户id
     * @param secret   用户的密码
     * @param time   token的有效时间 单位:毫秒
     * @return 加密的token
     */
    public static String createToken(String phone, Integer userId, String secret, Long time) {
        Date date = new Date(System.currentTimeMillis() + time);
        Algorithm algorithm = null;
        try {
            algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withClaim("phone", phone)
                    .withClaim("userId", String.valueOf(userId))
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 校验token是否正确
     *
     * @param token  密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public static boolean verify(String token, String secret) {
        try {
            // 根据密码生成JWT效验器
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("phone", getPhone(token))
                    .withClaim("userId", getUserId(token))
                    .build();
            // 效验TOKEN
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            return false;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }
    }



}
