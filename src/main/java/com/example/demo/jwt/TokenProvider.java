package com.example.demo.jwt;

import org.springframework.security.core.Authentication;

/**
 * author  Xiao Hong
 * date  2020/12/6 17:35
 * description
 */

public class TokenProvider {


    public boolean validateToken(String jwt) {
        return false;
    }

    public Authentication getAuthentication(String jwt) {
        return null;
    }
}
