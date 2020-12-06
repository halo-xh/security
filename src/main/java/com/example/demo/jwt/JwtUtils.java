package com.example.demo.jwt;

import io.jsonwebtoken.Jwts;

/**
 * author  Xiao Hong
 * date  2020/12/6 18:16
 * description
 */

public class JwtUtils {

    public static String generateTokenExpireInSeconds(Object userInfo,int expire){
        return Jwts.builder()
               .claim()
    }
}
