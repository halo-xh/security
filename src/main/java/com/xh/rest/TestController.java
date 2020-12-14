package com.xh.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Xiao Hong on 2020-12-14
 */
@RestController
@RequestMapping("/api")
public class TestController {
    
    @GetMapping("/test")
    public String test(){
        return "test successfully";
    }
    
    @PostMapping("/test")
    public String testp(){
        return "test post successfully";
    }
    
}
