package com.example.schoolcheck.user.controller;

import com.example.schoolcheck.common.version.Version;
import com.example.schoolcheck.user.dto.UserSigninReqDto;
import com.example.schoolcheck.user.dto.UserSigninResDto;
import com.example.schoolcheck.user.service.UserService;
import com.example.schoolcheck.user.dto.UserReqDto;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(Version.version + "/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody UserReqDto userReqDto){
        userService.createUser(userReqDto);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/sign-in")
    public ResponseEntity<UserSigninResDto> signIn(
            @RequestBody UserSigninReqDto userSigninDto
            ,HttpServletResponse response){
        var result = userService.signIn(userSigninDto, response);
        return ResponseEntity.ok(result);
    }


}

