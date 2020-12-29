package com.shiro.exception;

import com.shiro.common.JsonData;
import com.shiro.common.ResponseCode;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(AuthorizationException.class)
    @ResponseBody
    public JsonData shiroAuthorizationException() {
        return new JsonData(false, "请先进行登录", ResponseCode.FORBIDDEN);
    }


    /**
     * 无效的凭证（token）
     * @return
     */
    @ExceptionHandler(InvalidVoucherException.class)
    @ResponseBody
    public JsonData shiroInvalidVoucherException() {
        return new JsonData(true, "无效的凭证，请重新登录", ResponseCode.TOKEN_ERROR);
    }


}
