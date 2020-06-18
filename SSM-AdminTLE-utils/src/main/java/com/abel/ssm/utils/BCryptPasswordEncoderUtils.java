package com.abel.ssm.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/**
 * 使用 BCryptPasswordEncoder 加密
 */
public class BCryptPasswordEncoderUtils {

     private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public static String encodePassword(String password){
        return bCryptPasswordEncoder.encode(password);
    }

    public static void main(String[] args) {
        String password = "sherlock";
        String pwd = encodePassword(password);
        System.out.println(pwd);
    }
}
