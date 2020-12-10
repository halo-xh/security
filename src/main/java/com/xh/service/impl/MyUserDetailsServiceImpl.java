package com.xh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xh.domain.SubjectLogin;
import com.xh.mapper.SubjectLoginMapper;
import com.xh.security.LoginUser;
import com.xh.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by Xiao Hong on 2020-12-10
 */
@Service
public class MyUserDetailsServiceImpl implements MyUserDetailsService {
    
    @Autowired
    private SubjectLoginMapper subjectLoginMapper;
    
    @Override
    public LoginUser loadUserByUsername(String username) throws UsernameNotFoundException {
        SubjectLogin subjectLogin = subjectLoginMapper.selectByLoginName(username);
        if (subjectLogin != null) {
            LoginUser loginUser = new LoginUser();
            loginUser.setLoginname(subjectLogin.getLoginname());
            loginUser.setPassword(subjectLogin.getPassword());
            loginUser.setSid(subjectLogin.getSid());
            loginUser.setStatus(subjectLogin.getStatus());
            return loginUser;
        }else {
            throw new UsernameNotFoundException("UserDetailsService.userNotFound");
        }
    }
    
    
    
    
}
