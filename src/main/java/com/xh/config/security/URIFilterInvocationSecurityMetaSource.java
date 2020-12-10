package com.xh.config.security;

import com.xh.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Xiao Hong on 2020-12-10
 */
@Component("securityMetadataSource")
public class URIFilterInvocationSecurityMetaSource implements FilterInvocationSecurityMetadataSource {
    
    @Autowired
    private AuthorityService authorityService;
    
    @Value("app.url.white")
    private List<String> whiteUrlList;
    
    @Value("app.url.permitall")
    private boolean permitAllUrl;
    
    // <request api matcher,List<role_string>>
    private Map<RequestMatcher, Collection<ConfigAttribute>> requestMap;
    
    @Override
    public Collection<ConfigAttribute> getAttributes(Object invocation) throws IllegalArgumentException {
        final HttpServletRequest request = ((FilterInvocation) invocation).getRequest();
        Collection<ConfigAttribute> attrs = null;
        this.requestMap = authorityService.initAuthorityMap(permitAllUrl, whiteUrlList);
        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap.entrySet()) {
            if (entry.getKey().matches(request)) {
                attrs = entry.getValue();
                break;
            }
        }
        return attrs;
    }
    
    
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        this.requestMap = this.authorityService.initAuthorityMap(permitAllUrl, whiteUrlList);
        Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();
        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap
                .entrySet()) {
            allAttributes.addAll(entry.getValue());
        }
        return allAttributes;
    }
    
    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
