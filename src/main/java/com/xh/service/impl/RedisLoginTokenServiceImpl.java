package com.xh.service.impl;

import com.xh.domain.LoginToken;
import com.xh.service.LoginTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;

/**
 * Created by Xiao Hong on 2022-05-28
 * </p>
 */
@Primary
@Service
public class RedisLoginTokenServiceImpl implements LoginTokenService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public Boolean existsUserToken(String username, String authToken) {
        String tk = redisTemplate.opsForValue().get(getTkRedisKey(username));
        return tk != null && tk.equals(authToken);
    }

    @Override
    public void delIfExistsUserSession(String username) {
        redisTemplate.delete(getTkRedisKey(username));
    }

    @Override
    public void saveToken(LoginToken token) {
        Date expiredt = token.getExpiredt();
        Date createdt = token.getCreatedt();
        String tk = token.getToken();
        String user = token.getUser();
        redisTemplate.opsForValue().set(getTkRedisKey(user), tk, Duration.between(createdt.toInstant(), expiredt.toInstant()));
    }

    private static String getTkRedisKey(String userName) {
        return "Tk:" + userName;
    }

}
