package com.shiro.config;

import com.alibaba.fastjson.JSON;
import com.shiro.common.Constant;
import com.shiro.common.JsonData;
import com.shiro.common.ResponseCode;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName JwtFilter
 * @Description
 * @Author gaopeng
 * @Date 2020/12/26 20:07
 * @Version 1.0
 **/

public class JwtFilter extends BasicHttpAuthenticationFilter{


    /**
      * @Author gaopeng
      * @Description //
      * @Date 20:24 2020/12/26
      * @param
      * @return boolean
      **/
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        String token = ((HttpServletRequest) request).getHeader(Constant.TOKEN_HEADER_NAME);
        if (token != null) {
            return executeLogin(request, response);
        }
        // 如果请求头不存在 Token，则可能是执行登陆操作或者是游客状态访问，无需检查 token，直接返回 true
        return true;
    }


    /**
      * @Author gaopeng
      * @Description //
      * @Date 20:22 2020/12/26
      * @param 
      * @return boolean
      **/
    @Override
    protected boolean executeLogin(ServletRequest httpServletRequest, ServletResponse httpServletResponse)  {
        HttpServletRequest request = (HttpServletRequest) httpServletRequest;
        HttpServletResponse response = (HttpServletResponse) httpServletResponse;

        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        try {
            String token = request.getHeader(Constant.TOKEN_HEADER_NAME);
            JwtToken jwtToken = new JwtToken(token);
            getSubject(request, response).login(jwtToken);
        } catch (Exception e) {
            response.setStatus(HttpStatus.OK.value());
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter writer = null;
            try {
                writer = response.getWriter();
            } catch (IOException e1) {
                e1.printStackTrace();
                return false;
            }
            writer.print(JSON.toJSONString(new JsonData(false, "token错误", ResponseCode.TOKEN_ERROR)));

            return false;
        }
        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }


    /**
     * 对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }
}
