package com.example.schoolcheck.exception.dto;

import org.springframework.http.HttpStatus;

public record ExceptionDto(String errorCode,
                           HttpStatus httpStatus,
                           String message) {
}
