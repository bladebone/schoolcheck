package com.example.schoolcheck.user.dto;

import com.example.schoolcheck.user.User;
import com.example.schoolcheck.user.definition.Role;

public record UserReqDto(
        String email,
        String name,
        Role role,
        String pwd
) {
    public User toUser(String encryptedPwd){
        return User.builder()
                .email(this.email)
                .name(this.name)
                .pwd(encryptedPwd)
                .role(this.role)
                .build();
    }

}
