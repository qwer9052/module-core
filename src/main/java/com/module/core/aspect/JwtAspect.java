package com.module.core.aspect;

import com.module.core.exception.CommonException;
import com.module.core.jwt.JwtProvider;
import com.module.core.util.ListUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Aspect
@RequiredArgsConstructor
public class JwtAspect {

    Logger logger = LoggerFactory.getLogger(JwtAspect.class);
    private final HttpServletRequest httpServletRequest;
    private final JwtProvider jwtProvider;

    @Pointcut("@annotation(com.module.core.annotation.JwtAuth)")
    private void JwtAuth() {
    }

    @Around("JwtAuth()")
    public Object jwt(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("[JwtAspect] logging");
        Integer row = getName(joinPoint, "userId");
        Object[] args = joinPoint.getArgs();
        if(row != null){
            args[row] = getJwtAuthUserId();
        }
        return joinPoint.proceed(args);
    }

    private Integer getName(final ProceedingJoinPoint joinPoint, final String parameterName) {
        Integer row = null;
        final String[] parameterNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        for (int i = 0; i < parameterNames.length; i++) {
            System.out.println("parameterNames : " + parameterNames[i]);
            if (parameterNames[i].equals(parameterName)) {
                return i;
            }
        }
        return row;
    }

    private Long getJwtAuthUserId(){
        String authorization = httpServletRequest.getHeader("Authorization");
        Claims claims = jwtProvider.parseJwtToken(authorization);
        Long userId = Long.valueOf(String.valueOf(claims.get("userId")));
        return userId;
    }
}
