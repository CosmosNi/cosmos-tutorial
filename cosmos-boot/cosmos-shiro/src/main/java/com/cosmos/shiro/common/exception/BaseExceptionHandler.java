package com.cosmos.shiro.common.exception;

import com.cosmos.shiro.core.domain.ResponseMessage;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description 自定义异常
 * @Author cosmos
 * @CreateTime 2019/6/15 22:56
 */
@ControllerAdvice
public class BaseExceptionHandler {
    /**
     * 处理Shiro权限拦截异常
     * 如果返回JSON数据格式请加上 @ResponseBody注解
     *
     * @Author cosmos
     * @CreateTime 2019/6/15 13:35
     * @Return Map<Object> 返回结果集
     */
    @ResponseBody
    @ExceptionHandler(value = AuthorizationException.class)
    public ResponseMessage<String> defaultErrorHandler(AuthorizationException e) {

        return ResponseMessage.error("权限不足");
    }

    @ResponseBody
    @ExceptionHandler(value = IncorrectCredentialsException.class)
    public ResponseMessage<String> handleIncorrectCredentialsException(IncorrectCredentialsException e) {

        return ResponseMessage.error("用户不存在或者密码错误");
    }

    @ResponseBody
    @ExceptionHandler(value = LockedAccountException.class)
    public ResponseMessage<String> handleLockedAccountException(LockedAccountException e) {

        return ResponseMessage.error("登录失败，该用户已被冻结");
    }


    @ResponseBody
    @ExceptionHandler(value = AuthenticationException.class)
    public ResponseMessage<String> handleAuthenticationException(AuthenticationException e) {

        return ResponseMessage.error("该用户不存在");
    }
}
