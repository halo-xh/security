package com.xh.config;

import com.xh.jwt.JWTFilter;
import com.xh.security.AuthorityService;
import com.xh.security.DBAuthenticationProvider;
import com.xh.security.MyUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.core.env.Environment;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final Logger log = LoggerFactory.getLogger(SecurityConfiguration.class);

    @Autowired
    private Environment env;

    @Resource
    private FilterSecurityInterceptor filterSecurityInterceptor;

    @Autowired
    private JWTFilter jwtFilter;

    @Resource
    private HttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository;

    @Resource
    @Qualifier("logoutSuccessHandler")
    private HttpStatusReturningLogoutSuccessHandler logoutSuccessHandler;

    @Resource
    private MyUserDetailsService userDetailsService;

    @Resource
    private AuthorityService authorityService;

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers(HttpMethod.TRACE, "**").denyAll()
                .and()
                .csrf().disable()
                .addFilterBefore(filterSecurityInterceptor, FilterSecurityInterceptor.class)
//                .addFilterBefore(openEntityManagerInViewFilter, SecurityContextPersistenceFilter.class)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .headers().frameOptions().sameOrigin()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.logout().logoutSuccessHandler(logoutSuccessHandler);
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        List<AuthenticationProvider> providerList = new ArrayList<AuthenticationProvider>();
        providerList.add(dBAuthenticationProvider());
        ProviderManager authenticationManager = new ProviderManager(providerList);
        authenticationManager.setEraseCredentialsAfterAuthentication(true);
        return authenticationManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DBAuthenticationProvider dBAuthenticationProvider() {
        DBAuthenticationProvider dBAuthenticationProvider = new DBAuthenticationProvider();
        dBAuthenticationProvider.setUserDetailsService(userDetailsService);
        dBAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        dBAuthenticationProvider.setAuthorityService(authorityService);
        return dBAuthenticationProvider;
    }

    @Bean(name = "logoutSuccessHandler")
    public HttpStatusReturningLogoutSuccessHandler logoutSuccessHandler() {
        return new HttpStatusReturningLogoutSuccessHandler();
    }
    
}
