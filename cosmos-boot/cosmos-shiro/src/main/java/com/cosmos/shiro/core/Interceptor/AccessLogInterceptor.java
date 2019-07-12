package com.cosmos.shiro.core.Interceptor;

import cn.hutool.core.date.SystemClock;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.shiro.core.Interceptor
 * @ClassName: AccessLogInterceptor
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/7/12 14:35
 * @Version: 1.0
 */
@Component
public class AccessLogInterceptor extends HandlerInterceptorAdapter {
    private static final Logger log = LoggerFactory.getLogger(AccessLogInterceptor.class);

    public AccessLogInterceptor() {
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String uuid = UUID.randomUUID().toString();
        log.info(String.format("Request[%s] URL:[%s], Protocol:[%s], Params:%s", uuid, request.getRequestURL(), request.getProtocol(), JSONObject.toJSONString(request.getParameterMap())));
        request.setAttribute("startTime", SystemClock.now());
        request.setAttribute("uuid", uuid);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) throws Exception {
        long timeout = SystemClock.now() - (Long) request.getAttribute("startTime");
        String uuid = (String) request.getAttribute("uuid");
        log.info(String.format("Response[%s][%s] Timeout:[%s ms], ResponseStatus:[%s], ResponseBodySize:[%s], Error:[%s]", uuid, request.getRequestURI(), timeout, response.getStatus(), response.getBufferSize(), e != null ? String.valueOf(e) : "null"));
    }
}
