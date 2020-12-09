package com.xh.service;

import com.xh.jwt.TokenProvider;
import org.springframework.stereotype.Service;

/**
 * author  Xiao Hong
 * date  2020/12/7 21:27
 * description
 */

@Service
public class UserTokenService {

    public boolean existsUserToken(String loginMethodAndId, TokenProvider.TokenType tokenType, String authToken) {
        return false;
    }
}
