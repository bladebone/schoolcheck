package com.example.schoolcheck.exception;

import com.example.schoolcheck.exception.error.CustomError;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomException extends RuntimeException {


    private String errorCode;
    private HttpStatus httpStatus;
    private String message;

    public CustomException(CustomError e){
        super(e.getMessage());
        this.httpStatus = e.getHttpStatus();
        this.errorCode = e.getErrorCode();
    }

}
