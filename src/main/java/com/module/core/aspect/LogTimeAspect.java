package com.module.core.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class LogTimeAspect {

    Logger logger = LoggerFactory.getLogger(LogTimeAspect.class);

    @Pointcut("@annotation(com.module.core.annotation.LogExecutionTime)")
    private void LogTimeAspect() {
    }

    @Around("LogTimeAspect()")
    public Object AssumeExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object proceed = joinPoint.proceed();
        long totalTimeMillis = stopWatch.getTotalTimeMillis();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();

        logger.info("실행 메서드: {}, 실행시간 = {}ms", methodName, totalTimeMillis);
        return proceed;
    }
}
