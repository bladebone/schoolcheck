package com.example.schoolcheck.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserError implements CustomError {

    NOT_EXISTS_USER(HttpStatus.BAD_REQUEST,"존재하지 않는 유저입니다.", "UE001"),
    NOT_VALID_TOKEN(HttpStatus.UNAUTHORIZED,"유효한 토큰이 아닙니다.", "UE002")
    ;

    private HttpStatus httpStatus;
    private String message;
    private String errorCode;


}
