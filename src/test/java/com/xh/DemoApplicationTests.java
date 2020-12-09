package com.xh;

import com.xh.service.LoginTokenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    LoginTokenService loginTokenService;

    @Test
    void contextLoads() {
        System.out.println(loginTokenService.existsUserToken("test1", "test"));
    }

}
