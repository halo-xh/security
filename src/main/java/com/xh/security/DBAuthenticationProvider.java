package com.xh.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * author  Xiao Hong
 * date  2020/12/6 17:45
 * description
 */

public class DBAuthenticationProvider implements AuthenticationProvider {

    private MyUserDetailsService userDetailsService;

    private PasswordEncoder passwordEncoder;

    private AuthorityService authorityService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }

    public void setUserDetailsService(MyUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public void setAuthorityService(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }
}
