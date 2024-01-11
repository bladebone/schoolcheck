package com.example.schoolcheck.user.service.Impl;

import com.example.schoolcheck.common.authentication.CookieProvider;
import com.example.schoolcheck.common.authentication.TokenProvider;
import com.example.schoolcheck.common.authentication.definition.ValidTime;
import com.example.schoolcheck.common.authentication.util.Aes256Util;
import com.example.schoolcheck.common.exception.DataNotFoundException;
import com.example.schoolcheck.user.User;
import com.example.schoolcheck.user.dto.UserReqDto;
import com.example.schoolcheck.user.dto.UserSigninReqDto;
import com.example.schoolcheck.user.dto.UserSigninResDto;
import com.example.schoolcheck.user.repository.UserRepository;
import com.example.schoolcheck.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final RedisTemplate<String, String> redisTemplate;//string prefix, 스프링 추상화 메소드 찾아서 jwt토큰 저장하는 방법알아보기
    private final CookieProvider cookieProvider;

    @Override
    public void createUser(UserReqDto userReqDto) {
        //간략하게 푸쉬(서버에러안나게) - 우선순위!!!
        //토큰 재발급이나, 다양한 api 확인하기
        String encryptPwd = Aes256Util.encrypt(userReqDto.pwd()); //비밀번호 단방향 암호화로 바꾸기
        userRepository.save(userReqDto.toUser(encryptPwd));
    }

    @Override
    public UserSigninResDto signIn(UserSigninReqDto userSigninDto, HttpServletResponse response) {
        User user = userRepository.findByEmail(userSigninDto.email())
                .orElseThrow(() -> new DataNotFoundException("해당하는 유저를 찾을 수 없습니다."));

        if (!Aes256Util.decrypt(user.getPwd()).equals(userSigninDto.pwd())) {
            throw new DataNotFoundException("비밀번호가 일치하지 않습니다.");
        }

        String accessToken = tokenProvider.generateToken(
                user.getEmail(),
                user.getRole(),
                false);

        String refreshToken;

        if (Boolean.FALSE.equals(redisTemplate.hasKey(user.getEmail()))) {
            refreshToken = tokenProvider.generateToken(
                    user.getEmail(),
                    user.getRole(),
                    true);

            redisTemplate.opsForValue().set(
                    user.getEmail(),
                    refreshToken,
                    ValidTime.REFRESH_TOKEN_VALID_TIME.getTime(),
                    TimeUnit.MILLISECONDS
            );
        } else {
            refreshToken = (String) redisTemplate.opsForValue().get(user.getEmail());
        }

        cookieProvider.setRefreshTokenCookie(refreshToken, response);

        return new UserSigninResDto(accessToken);
    }
}
