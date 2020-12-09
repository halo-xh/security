package com.example.demo.security;

import com.example.demo.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * author  Xiao Hong
 * date  2020/12/6 17:00
 * description
 */

public class TokenAuthentication implements Authentication {
    
    private String token;
    
    @Autowired
    private SubjectRepository subjectRepository;
    
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
    
    @Override
    public Object getCredentials() {
        return null;
    }
    
    @Override
    public Object getDetails() {
        return null;
    }
    
    @Override
    public Object getPrincipal() {
        return null;
    }
    
    @Override
    public boolean isAuthenticated() {
        return false;
    }
    
    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
    
    }
    
    @Override
    public String getName() {
        return null;
    }
    
    public String getToken() {
        return token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
    
    
}
