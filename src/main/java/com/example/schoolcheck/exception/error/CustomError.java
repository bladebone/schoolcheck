package com.example.schoolcheck.exception.error;


import org.springframework.http.HttpStatus;

public interface CustomError {

    String getMessage();

    String getErrorCode();

    HttpStatus getHttpStatus();

    //Spring bind Error 객체 -> 커스텀을 만들 때 일반적인 객체를 지원하는지 확인
    //root -> common -> exception
    //인증 / 인가를 확실히 구현할 것
    //type,util -> 만능 표현,
}
