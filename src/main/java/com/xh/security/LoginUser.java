package com.xh.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Xiao Hong on 2020-12-10
 */
public class LoginUser implements UserDetails {
    
    private Integer sid;
    private char[] password = new char[] {};
    private String loginname;
    private String status;
    
    private Collection<GrantedAuthority> authorities;
    private Collection<String> webURIs;
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authorities == null) {
            return null;
        }
        ArrayList<GrantedAuthority> arrayList = new ArrayList<>(authorities);
        return (Collection<GrantedAuthority>) arrayList.clone();
    }
    
    @Override
    public String getPassword() {
        return new String(this.password);
    }
    
    @Override
    public String getUsername() {
        return loginname;
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return "A".equals(status);
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    @Override
    public boolean isEnabled() {
        return ("A".equals(this.status));
    }
    
    public Integer getSid() {
        return sid;
    }
    
    public void setSid(Integer sid) {
        this.sid = sid;
    }
    
    public void setPassword(String password) {
        if(password==null) {
            this.password = new char[] {};
        }else {
            this.password = password.toCharArray();
        }
    }
    
    public String getLoginname() {
        return loginname;
    }
    
    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public void setAuthorities(Collection<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
    
    public Collection<String> getWebURIs() {
        if (webURIs == null) {
            return null;
        }
        ArrayList<String> arrayList = new ArrayList<>(webURIs);
        return (Collection<String>) arrayList.clone();
    }
    
    public void setWebURIs(Collection<String> webURIs) {
        if (webURIs == null) {
            this.webURIs = null;
        } else {
            ArrayList<String> arrayList = new ArrayList<>(webURIs);
            this.webURIs =  (Collection<String>)arrayList.clone();
        }
    }
}
