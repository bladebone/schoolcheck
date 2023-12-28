package com.example.schoolcheck.auth.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {

    Level level() default  Level.ROLE_USER;
    enum Level {
        ROLE_USER,
        ROLE_ADMIN
    }

}
