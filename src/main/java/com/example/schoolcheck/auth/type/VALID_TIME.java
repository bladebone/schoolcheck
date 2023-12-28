package com.example.schoolcheck.auth.type;

import lombok.Getter;

@Getter
public enum VALID_TIME {
    //accessToken 유효 시간
    ACCESS_TOKEN_VALID_TIME(1000L * 60 * 60 * 2),
    //refreshToken 유효 시간
    REFRESH_TOKEN_VALID_TIME(1000L * 60 * 60 * 24 * 30)
    ;
    private final long time;

    VALID_TIME(long time) {
        this.time = time;
    }

    // enum은 클래스적
    // type -> definition
}
