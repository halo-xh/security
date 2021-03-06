package com.xh.service;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.jaas.AuthorityGranter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * author  Xiao Hong
 * date  2020/12/6 17:48
 * description
 */

public interface AuthorityService {

    /**
     * 可以 利用缓存优化 缓存
     */
    Map<RequestMatcher, Collection<ConfigAttribute>> initAuthorityMap(boolean permitAllUrl, List<String> whiteUrlList);
    
    Collection<GrantedAuthority> getGrantedAuthorityByLoginName(Integer loginName);
    
}
