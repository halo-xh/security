package com.xh.jwt;

import com.xh.service.LoginTokenService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * author  Xiao Hong
 * date  2020/12/6 17:35
 * description
 */

@Component
public class TokenProvider {
    
    private final Logger log = LoggerFactory.getLogger(TokenProvider.class);
    
    private Key key = Keys.hmacShaKeyFor("kllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllley".getBytes());//todo.

    @Autowired
    private LoginTokenService userTokenService;
    
    public boolean validateToken(String jwt) {
        try {
            Jws<Claims> jws = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt);
            return validateUserToken(jws.getBody(), jwt);
        } catch (SignatureException e) {
            log.info("Invalid JWT signature.");
        } catch (MalformedJwtException e) {
            log.info("Invalid JWT token.");
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token.");
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token.");
        } catch (IllegalArgumentException e) {
            log.info("JWT token compact of handler are invalid.");
        }
        SecurityContextHolder.clearContext();
        return false;
    }
    
    public Authentication getAuthentication(String jwt) {
        return null;
    }
    
    private boolean validateUserToken(Claims jwtBody, String authToken) {
        String loginMethodAndId = jwtBody.getSubject();
        String username = String.valueOf(jwtBody.get("login_name"));
        boolean existsUserToken = userTokenService.existsUserToken(username, authToken); //todo. chk in db
        if (!existsUserToken) {
            SecurityContextHolder.clearContext();
            log.info("User session token not found in database for {}, probably logged out by another session.", loginMethodAndId);
        }
        return existsUserToken;
    }
    
    private String buildToken(Authentication authentication, Date validity){
        String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        String principal = (String) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject("loginName")
                .claim("auth", authorities)
                .claim(TokenPayload.USERNAME, principal)
                .signWith(key, SignatureAlgorithm.HS512).setExpiration(validity).compact();
    }
    

    private static class TokenPayload{
        
        public static final String USERNAME = "username";

    }
}
