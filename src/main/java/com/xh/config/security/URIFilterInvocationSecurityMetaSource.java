package com.xh.config.security;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.Collection;
import java.util.Map;

/**
 * Created by Xiao Hong on 2020-12-10
 */
public class URIFilterInvocationSecurityMetaSource implements FilterInvocationSecurityMetadataSource {
    
    // <request api matcher,List<role_string>>
    private Map<RequestMatcher, Collection<ConfigAttribute>> requestMap;
    
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        return null;
    }
    
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }
    
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
}
