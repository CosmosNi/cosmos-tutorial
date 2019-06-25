package com.cosmos.log.commmon.util;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @ProjectName: cosmos-log
 * @Package: com.cosmos.log.util
 * @ClassName: ServletUtil
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/6/6 14:11
 * @Version: 1.0
 */
public class ServletUtil {

    public static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }

    /**
     * 获取request
     */
    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }
}
