package com.example.schoolcheck.user.service;


import com.example.schoolcheck.user.dto.UserReqDto;
import com.example.schoolcheck.user.dto.UserSigninReqDto;
import com.example.schoolcheck.user.dto.UserSigninResDto;
import jakarta.servlet.http.HttpServletResponse;

public interface UserService {

    void createUser(UserReqDto userReqDto);

    UserSigninResDto signIn(UserSigninReqDto userSigninDto, HttpServletResponse response);


}
