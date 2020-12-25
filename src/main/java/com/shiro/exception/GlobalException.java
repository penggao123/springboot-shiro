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
        return new JsonData(false, "您无权访问该方法", ResponseCode.FORBIDDEN);
    }
}
