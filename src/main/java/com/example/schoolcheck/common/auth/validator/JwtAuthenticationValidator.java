package com.example.schoolcheck.common.auth.validator;

import io.jsonwebtoken.JwtException;
import lombok.Getter;

@Getter
public class JwtAuthenticationValidator extends JwtException {


    public JwtAuthenticationValidator(String message) {
        super(message);
    }

    public JwtAuthenticationValidator(String message, Throwable cause) {
        super(message, cause);
    }
}
