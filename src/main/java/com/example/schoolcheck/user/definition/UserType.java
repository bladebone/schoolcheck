package com.example.schoolcheck.user.definition;

public enum UserType {
    ROLE_STUDENT("student"),
    ROLE_TEACHER("teacher");

    private String job;

    UserType(String job) {
        this.job = job;
    }
}
