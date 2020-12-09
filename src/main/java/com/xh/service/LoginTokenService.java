package com.xh.service;

import com.xh.domain.LoginToken;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Xiao Hong
 * @since 2020-12-09
 */
public interface LoginTokenService extends IService<LoginToken> {

    boolean existsUserToken(String username, String authToken);
}
