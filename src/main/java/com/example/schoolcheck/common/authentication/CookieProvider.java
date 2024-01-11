package com.example.schoolcheck.common.authentication;

import com.example.schoolcheck.common.authentication.definition.ValidTime;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

@Component
public class CookieProvider {
    private final String refreshTokenName = "refreshToken";

    public void setRefreshTokenCookie(String refreshToken, HttpServletResponse response){
        Cookie cookie = new Cookie(refreshTokenName, refreshToken);
        cookie.setMaxAge((int) ValidTime.REFRESH_TOKEN_VALID_TIME.getTime());
        cookie.setPath("/");
        cookie.setSecure(false);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }
}
