package com.example.schoolcheck.common.authentication.definition;

import lombok.Getter;

@Getter
public enum ValidTime {
    //accessToken 유효 시간
    ACCESS_TOKEN_VALID_TIME(1000L * 60 * 60 * 2),
    //refreshToken 유효 시간
    REFRESH_TOKEN_VALID_TIME(1000L * 60 * 60 * 24 * 30)
    ;
    private final long time;

    ValidTime(long time) {
        this.time = time;
    }

}
