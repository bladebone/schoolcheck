package com.example.schoolcheck.auth.util;

import com.example.schoolcheck.auth.TokenProvider;
import com.example.schoolcheck.exception.CustomException;
import com.example.schoolcheck.exception.error.UserError;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class AuthAspect {

    private final HttpServletRequest servletRequest;
    private final TokenProvider tokenProvider;

    @Before("@annotation(Auth)")
    public void checkAuth(JoinPoint joinPoint) {

        String token = servletRequest.getHeader("Authorization");

        if(token != null && tokenProvider.validateToken(token)){
            throw new CustomException(UserError.NOT_VALID_TOKEN);
        }


    }
}
