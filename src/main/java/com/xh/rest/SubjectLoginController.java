package com.xh.rest;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.deploy.xml.BadTokenException;
import com.xh.common.ResponseObject;
import com.xh.jwt.JWTFilter;
import com.xh.jwt.TokenProvider;
import com.xh.rest.vo.LoginVM;
import com.xh.service.LoginTokenService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.security.GeneralSecurityException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Xiao Hong
 * @since 2020-12-09
 */
@RestController
@RequestMapping("/blog/subject-login")
public class SubjectLoginController {
    
    private final TokenProvider tokenProvider;
    
    private final AuthenticationManager authenticationManager;
    
    private final LoginTokenService loginTokenService;
    
    public SubjectLoginController(TokenProvider tokenProvider, AuthenticationManager authenticationManager, LoginTokenService loginTokenService) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
        this.loginTokenService = loginTokenService;
    }
    
    @PostMapping("/authenticate")
    public ResponseEntity<JWTToken> authenticate(@RequestBody LoginVM loginVM){
        Authentication authentication =null;
        try {
            authentication = createAuthentication(loginVM);
        }catch (Exception e){
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("errorCode", "801");
            httpHeaders.add("error", e.getLocalizedMessage());
            httpHeaders.add("username",loginVM.getUsername());
            return new ResponseEntity<>(httpHeaders, HttpStatus.UNAUTHORIZED);
        }
        //Failed to authenticate
        if (authentication == null) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("error", "Authentication is null");
            return new ResponseEntity<>(httpHeaders, HttpStatus.UNAUTHORIZED);
        } else {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            loginTokenService.delIfExistsUserSession(loginVM.getUsername());
            return buildNewUserSessionResponse(authentication);
        }
    
    }
    
    private ResponseEntity<JWTToken> buildNewUserSessionResponse(Authentication authentication) {
        String jwt = tokenProvider.createToken(authentication,true);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
        return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
    }
    
    private Authentication createAuthentication(LoginVM loginVM) {
         UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(loginVM.getUsername(),loginVM.getPassword());
        Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
        loginVM.setPassword(new char[] {});
        return authentication;
    }
    
    static class JWTToken {
        
        private String idToken;
        
        JWTToken(String idToken) {
            this.idToken = idToken;
        }
        
        @JsonProperty("id_token")
        String getIdToken() {
            return idToken;
        }
        
        void setIdToken(String idToken) {
            this.idToken = idToken;
        }
    }
}
