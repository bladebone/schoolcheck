package com.example.schoolcheck.common.authentication.aspect;

import com.example.schoolcheck.common.authentication.TokenProvider;
import com.example.schoolcheck.common.exception.JwtAuthenticationException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationAspect {
    private TokenProvider tokenProvider;
    @Before("execution(* com.example.schoolcheck.*.*(..))")
    public void authenticate(JoinPoint joinPoint) throws Exception {

        HttpServletRequest request = ((ServletRequestAttributes)
                RequestContextHolder.currentRequestAttributes()).getRequest();

        String token = tokenProvider.resolveToken(request);

        if (token == null || !tokenProvider.validateToken(token)) {
            throw new JwtAuthenticationException("유효하지 않은 토큰입니다.");
        }

    }
}
