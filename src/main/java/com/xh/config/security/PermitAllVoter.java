package com.xh.config.security;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;

import java.util.Collection;

/**
 * permit all.
 *
 * Created by Xiao Hong on 2020-12-10
 */
public class PermitAllVoter implements AccessDecisionVoter<Object> {

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return attribute instanceof PermitAllConfigAtrribute;
    }
    
    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
    
    @Override
    public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
        for (ConfigAttribute attribute : attributes) {
            if (supports(attribute)) {
                return 1;
            }
        }
        return 0;
    }
}
