package com.example.schoolcheck.exception;


import com.example.schoolcheck.exception.dto.ExceptionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = RestController.class)
public class CustomErrorHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionDto> handleCustomException(CustomException e){
        return ResponseEntity.status(e.getHttpStatus())
                .body(
                        new ExceptionDto(
                                e.getErrorCode(),
                                e.getHttpStatus(),
                                e.getMessage())
                );
    }
}
