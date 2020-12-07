package com.example.demo.jwt;

import com.example.demo.service.UserTokenService;
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
    
    private Key key = Keys.hmacShaKeyFor("key".getBytes());//todo.

    @Autowired
    private UserTokenService userTokenService;
    
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
        TokenType tokenType = TokenType.valueOf(String.valueOf(jwtBody.get(TokenPayload.TOKEN_TYPE)));
        boolean existsUserToken = userTokenService.existsUserToken(loginMethodAndId, tokenType, authToken); //todo. chk in db
        if (!existsUserToken) {
            SecurityContextHolder.clearContext();
            log.info("User session token not found in database for {}, probably logged out by another session.", loginMethodAndId);
        }
        return existsUserToken;
    }
    
    private String buildToken(Authentication authentication, Date validity, TokenType tokenType){
        String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        
        String authMethod = ""; //
        String subjectUUId = "";//
        
        return Jwts.builder()
                .setSubject("loginmethod/loginName")
                .claim("auth", authorities).claim(TokenPayload.SUBJECT_UUID, subjectUUId)
                .claim(TokenPayload.TOKEN_TYPE, tokenType)
                .signWith(key, SignatureAlgorithm.HS512).setExpiration(validity).compact();
    }
    
    public enum TokenType {
        ACCESS_TOKEN, SESSION_TOKEN
    }
    
    
    public class TokenPayload {
        
        public static final String SUBJECT_UUID = "subjectUUID";
        
        public static final String TOKEN_TYPE = "tokenType";
        
        private static final long serialVersionUID = 1L;
        
        /**
         * Database Id of subject, different from login name
         */
        private String subjectUUID;
        
        private String tokenType;
        
        /**
         * Get Database Id of subject, different from login name
         *
         * @return Database Id of subject
         */
        public String getSubjectUUID() {
            return subjectUUID;
        }
        
        public String getTokenType() {
            return tokenType;
        }
        
        public TokenPayload subjectUUID(String subjectUUID) {
            this.subjectUUID = subjectUUID;
            return this;
        }
        
        public TokenPayload tokenType(String subjectUUID, String tokenType) {
            this.subjectUUID = subjectUUID;
            this.tokenType = tokenType;
            return this;
        }
        
        
    }
}
