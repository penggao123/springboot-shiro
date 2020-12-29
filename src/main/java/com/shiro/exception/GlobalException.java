package com.shiro.exception;

import com.shiro.common.JsonData;
import com.shiro.common.ResponseCode;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
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


    /**
     * 非法参数
     * @return
     */
    @ExceptionHandler(ParamException.class)
    @ResponseBody
    public JsonData paramException() {
        return new JsonData(true, "非法参数", ResponseCode.PARAM_ERROR);
    }





    /**
     * http请求类型错误
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public JsonData httpRequestMethodNotSupportedException() {
        return new JsonData(true, "Http请求类型错误", ResponseCode.HTTP_REQUEST_TYPE_ERROR);
    }






}
