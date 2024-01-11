package com.example.schoolcheck.common.authentication.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Slf4j
@Component
public class PasswordEncoder {

    public String getSalt(){
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[20];

        secureRandom.nextBytes(salt);

        StringBuffer sb = new StringBuffer();
        for(byte b : salt){
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }


    public String getEncrypt(String pwd, String salt){
        String result = "";

        try{

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update((pwd+salt).getBytes());
            byte[] pwdSalt = md.digest();

            StringBuffer sb = new StringBuffer();
            for(byte b : pwdSalt){
                sb.append(String.format("%02x", b));
            }

            result = sb.toString();

        } catch (NoSuchAlgorithmException e){
            log.error("잘못된 알고리즘 사용");
        }
        return result;
    }
}
