package com.xh.mapper;

import com.xh.domain.LoginToken;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Xiao Hong
 * @since 2020-12-09
 */
public interface LoginTokenMapper extends BaseMapper<LoginToken> {

    int saveToken(LoginToken token);
}
