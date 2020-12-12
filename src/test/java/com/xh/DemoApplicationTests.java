package com.xh;

import com.xh.domain.LoginToken;
import com.xh.domain.SubjectLogin;
import com.xh.mapper.SubjectLoginMapper;
import com.xh.service.LoginTokenService;
import com.xh.service.SubjectLoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    LoginTokenService loginTokenService;

    @Test
    void contextLoads() {
        System.out.println(loginTokenService.existsUserToken("test1", "test"));
    }

    @Test
    void getPwd() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("123456"));
        System.out.println(passwordEncoder.matches(
                "123456",
                "$2a$2a$10$TUwG9umkDdTNmJfb68yY5OxInoiIIL.zvAPzLPaHtO7EkFIPgTZ72"));
    }

    @Autowired
    SubjectLoginService subjectLoginService;

    @Test
    void addUser() {
        SubjectLogin test = SubjectLogin.builder().loginname("test")
                .password("$2a$10$gBuZh6WxB7wroRq/vFnIG.ewhwmBxfZDC/J1O9GPLn7MmWjhObC5i")
                .version(1)
                .build();
        subjectLoginService.save(test);
    }

    @Autowired
    private LoginTokenService userTokenService;

    @Test
    void addToken() {
        LoginToken loginToken = new LoginToken();
        loginToken.setExpiredt(new Date());
        loginToken.setUser("123");
        loginToken.setToken("jwtToken");
        userTokenService.saveToken(loginToken);
    }


}
