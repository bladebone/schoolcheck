package com.example.schoolcheck.common.authentication;

import com.example.schoolcheck.common.authentication.definition.ValidTime;
import com.example.schoolcheck.user.definition.Role;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Date;

@Slf4j
@Component
public class TokenProvider {

    @Value("${jwt.secretKey}")
    private String secretKey;

    private static final String KEY_ROLES = "role";
    private static final String BEARER_TYPE = "Bearer ";
    private static final String AUTHORIZATION_KEY = "Authorization";


    public String generateToken(String email, Role role, boolean isRefresh) {

        Claims claims = Jwts.claims().setSubject(email);
        claims.put("KEY_ROLES", role);

        Date now = new Date();
        Date expiredDate;

        if (!isRefresh) {
            expiredDate = new Date(now.getTime() + ValidTime.ACCESS_TOKEN_VALID_TIME.getTime());
        } else {
            expiredDate = new Date(now.getTime() + ValidTime.REFRESH_TOKEN_VALID_TIME.getTime());
        }

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }


    public String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader(AUTHORIZATION_KEY);
        if (bearerToken != null && bearerToken.startsWith(BEARER_TYPE)){
            return bearerToken.substring(7);
        }
        return null;
    }
    public String getAuthenticationByToken(String token){
        return String.valueOf(Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get(KEY_ROLES));
    }


    public Boolean validateToken(String token){
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
            throw new JwtException("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
            throw new JwtException("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
            throw new JwtException("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException | SignatureException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
            throw new JwtException("JWT 토큰이 잘못되었습니다.");
        }
    }
}
