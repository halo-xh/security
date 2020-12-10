package com.xh.config.security;

import org.springframework.security.access.ConfigAttribute;

/**
 * flag class.just means permit all ConfigAttribute
 *
 * Created by Xiao Hong on 2020-12-10
 */
public class PermitAllConfigAtrribute implements ConfigAttribute {
    
    @Override
    public String getAttribute() {
        return null;
    }
}
