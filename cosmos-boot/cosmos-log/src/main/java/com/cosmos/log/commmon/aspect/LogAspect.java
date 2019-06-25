package com.cosmos.log.commmon.aspect;

import com.alibaba.fastjson.JSONObject;
import com.cosmos.log.commmon.annotation.Log;
import com.cosmos.log.commmon.config.LogConfig;
import com.cosmos.log.commmon.enums.BusinessStatus;
import com.cosmos.log.commmon.util.AddressUtil;
import com.cosmos.log.commmon.util.IpUtils;
import com.cosmos.log.commmon.util.ServletUtil;
import com.cosmos.log.core.dto.SysLogDTO;
import com.cosmos.log.core.service.SysLogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * @ProjectName: cosmos-log
 * @Package: com.cosmos.log.aspect
 * @ClassName: LogAspect
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/6/6 14:03
 * @Version: 1.0
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    @Resource(name = "sysLogQueueThreadPool")
    private ExecutorService logQueueThreadPool;

    @Autowired
    private SysLogService sysLogService;

    private final ThreadLocal<Long> startTime = new ThreadLocal<Long>();

    private final ThreadLocal<Long> endTime = new ThreadLocal<Long>();
    @Autowired
    private LogConfig logConfig;

    /**
     * 配置织入点
     */
    @Pointcut("@annotation(com.cosmos.log.annotation.Log)")
    public void logPointCut() {

    }


    @Around("logPointCut()")
    public Object times(ProceedingJoinPoint joinPoint) throws Throwable {

        startTime.set(System.currentTimeMillis());
        Object obj = joinPoint.proceed();
        endTime.set(System.currentTimeMillis());
        return obj;

    }

    /**
     * 正常日志
     *
     * @param joinPoint
     */
    @AfterReturning(pointcut = "logPointCut()")
    public void handle(JoinPoint joinPoint) {
        handleDatabaseLog(joinPoint, null);
    }

    /**
     * 异常日志
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "logPointCut()", throwing = "e")
    public void handleException(JoinPoint joinPoint, Exception e) {
        handleDatabaseLog(joinPoint, e);
    }

    protected void handleDatabaseLog(final JoinPoint joinPoint, final Exception e) {

        try {
            Log log = getAnnotationLog(joinPoint);
            String ip = IpUtils.getIpAddr(ServletUtil.getRequest());


            SysLogDTO sysLogDTO = SysLogDTO.builder()
                    .executionTime(endTime.get() - startTime.get())
                    .description(log.description())
                    .operFunction(log.operFunction())
                    .operUrl(ServletUtil.getRequest().getRequestURI())
                    .operIp(ip)
                    .operLocation(AddressUtil.getAddress(ip))
                    .operModule(logConfig.getModuleId())
                    .operSystem(logConfig.getSystemId())
                    .operName("anonymity")
                    .operStatus(BusinessStatus.SUCCESS)
                    .operMethod(joinPoint.getTarget().getClass().getName() + "" + joinPoint.getSignature().getName())
                    .operParam(setRequestValue(log, joinPoint))
                    .build();
            if (e != null) {
                sysLogDTO.setOperStatus(BusinessStatus.FAIL);
                sysLogDTO.setErrorMsg(e.getMessage());
            }
            logQueueThreadPool.execute(() -> {

                sysLogService.saveLog(sysLogDTO);


            });
        } catch (Exception e1) {
            e1.printStackTrace();
        }


    }

    /**
     * 获取请求的参数，放到log中
     *
     * @throws Exception 异常
     */
    private String setRequestValue(Log log, JoinPoint joinPoint) throws Exception {
        Map<String, String[]> map = ServletUtil.getRequest().getParameterMap();
        String params = JSONObject.toJSONString(map);
        if (log.saveParams()) {
            // 获取参数的信息，传入到数据库中。
            return params;
        }
        return null;
    }

    /**
     * 返回log注解
     *
     * @param joinPoint
     * @return
     * @throws Exception
     */
    private Log getAnnotationLog(JoinPoint joinPoint) throws Exception {

        Signature signature = joinPoint.getSignature();

        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();


        return method.getAnnotation(Log.class);

    }
}
