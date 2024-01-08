package com.example.schoolcheck.common.exception;

import io.jsonwebtoken.JwtException;
import lombok.Getter;

@Getter
public class JwtAuthenticationException extends JwtException {


    public JwtAuthenticationException(String message) {
        super(message);
    }

    public JwtAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
