package com.xh.service;

import com.xh.domain.LoginToken;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Xiao Hong
 * @since 2020-12-09
 */
public interface LoginTokenService  {

    Boolean existsUserToken(String username, String authToken);

    void delIfExistsUserSession(String username);

    void saveToken(LoginToken token);
}
