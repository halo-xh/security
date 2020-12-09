package com.xh.security;

import org.springframework.security.authentication.jaas.AuthorityGranter;

import java.security.Principal;
import java.util.Set;

/**
 * author  Xiao Hong
 * date  2020/12/6 17:48
 * description
 */

public class AuthorityService implements AuthorityGranter {

    @Override
    public Set<String> grant(Principal principal) {
        return null;
    }
}
