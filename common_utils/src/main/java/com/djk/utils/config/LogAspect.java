package com.djk.utils.config;

import com.djk.utils.Log;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by dujinkai on 2018/6/30.
 * 操作日志切面
 */
@Component
@Aspect
@Slf4j
public class LogAspect {

    /**
     * 切点
     */
    @Pointcut("@annotation(com.djk.utils.Log)")
    public void pointCut() {

    }

    /**
     * 方法执行前切入
     */
    @Before("pointCut()")
    public void printLog(JoinPoint joinPoint) {


        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        Log syslog = method.getAnnotation(Log.class);

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        //请求的参数
        Object[] args = joinPoint.getArgs();

        log.debug("begin to execute className:{} \r\n methodName:{} \r\n logDesc:{} \r\n params:{}", className, methodName, syslog.value(), args);
    }
}
