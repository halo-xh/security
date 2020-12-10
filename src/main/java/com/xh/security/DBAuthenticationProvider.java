package com.xh.security;

import com.xh.service.AuthorityService;
import com.xh.service.MyUserDetailsService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * author  Xiao Hong
 * date  2020/12/6 17:45
 * description
 */

public class DBAuthenticationProvider extends DaoAuthenticationProvider {
    
    private MyUserDetailsService userDetailsService;
    
    private PasswordEncoder passwordEncoder;
    
    private AuthorityService authorityService;
    
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        this.logger.info("additionalAuthenticationChecks()");
        if (authentication.getCredentials() == null) {
            this.logger.debug("Authentication failed: no credentials provided");
            throw new BadCredentialsException(this.messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.badCredentials",
                    "Bad credentials"));
        }
        String loginname = ((LoginUser) userDetails).getLoginname();
        String presentedPassword = authentication.getCredentials().toString();
        if (this.getPasswordEncoder().matches(presentedPassword, userDetails.getPassword())) {
            // may expire check.todo
            
            Collection<GrantedAuthority> authorities = authorityService.getGrantedAuthorityByLoginName(((LoginUser) userDetails).getSid());
            ((LoginUser) userDetails).setAuthorities(authorities);
            return;
        }
        this.logger.debug("Authentication failed: password does not match stored value");
        throw new BadCredentialsException(this.messages.getMessage(
                "AbstractUserDetailsAuthenticationProvider.badCredentials",
                "Bad credentials"));
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
