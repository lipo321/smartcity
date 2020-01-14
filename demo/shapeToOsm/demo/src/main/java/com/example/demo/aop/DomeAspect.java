package com.example.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 将几何shape要素转换成OsmObject
 *
 * @author lipo@126.com
 * @version 2019年7月4日
 */
@Slf4j
@Aspect
@Component
public class DomeAspect {
    /**
     * service 切点
     */
    @Pointcut("execution(* com.example.demo.service.*.*(..))")
    public void graphServiceAll() {
    }

    /**
     * controller 切点
     */
    @Pointcut(" execution(* com.example.demo.controller.*.*(..))")
    public void graphControllerAll() {
    }


    /**
     * 环绕切法
     */
    @Around("graphServiceAll()")
    public Object graphServiceAllAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("**********【graphAllStart】**********");

        log.info("==========【graphAllBefore】==========");
        /** 调用方法信息 */
        methodInfoLog(joinPoint);
        /** 执行原方法本身 */
        Object result = joinPoint.proceed();
        log.info("==========【graphAllAfter】==========");
        return result;
    }


    /**
     * 环绕切法切controller
     */
    @Around("graphControllerAll()")
    public Object graphControllerAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("**********【controllerStart】**********");

        log.info("==========【controllerBefore】==========");
        /** 调用方法信息 */
        methodInfoLog(joinPoint);
        /** 执行原方法本身 */
        Object result = joinPoint.proceed();
        log.info("==========【controllerAfter】==========");
        /** http请求信息 */
        httpInfoLog(joinPoint);
        return result;
    }


    private void methodInfoLog(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        log.info("【method】：{}", signature.getName());
        log.info("【package】: {}", signature.getDeclaringTypeName());
        log.info("【type】: {}", signature.getDeclaringType());

        MethodSignature methodSignature = (MethodSignature) signature;
        String[] strings = methodSignature.getParameterNames();
        log.info("【param】：{}", Arrays.toString(strings));
        log.info("【paramValue】: {}", Arrays.toString(joinPoint.getArgs()));
    }

    private void httpInfoLog(JoinPoint joinPoint) {
        /** 接收到请求，记录请求内容 */
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        /** 记录下请求内容 */
        log.info("【requestURL】: {}", request.getRequestURL().toString());
        log.info("【requestMethod】: {}", request.getMethod());
        log.info("【ip】: {}", request.getRemoteAddr());
        log.info("【classMethod】: {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
    }
}
