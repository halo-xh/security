package com.xh;

import com.xh.domain.SubjectLogin;
import com.xh.mapper.SubjectLoginMapper;
import com.xh.service.LoginTokenService;
import com.xh.service.SubjectLoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    LoginTokenService loginTokenService;

    @Test
    void contextLoads() {
        System.out.println(loginTokenService.existsUserToken("test1", "test"));
    }

    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Test
    void getPwd(){
        //System.out.println(passwordEncoder.encode("123456"));
    }
    
    @Autowired
    SubjectLoginService subjectLoginService;
    
    @Test
    void addUser(){
        SubjectLogin test = SubjectLogin.builder().loginname("test")
                .password("$2a$10$gBuZh6WxB7wroRq/vFnIG.ewhwmBxfZDC/J1O9GPLn7MmWjhObC5i")
                .build();
        subjectLoginService.save(test);
    }
}
