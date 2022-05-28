package com.xh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xh.domain.LoginToken;
import com.xh.mapper.LoginTokenMapper;
import com.xh.service.LoginTokenService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Xiao Hong
 * @since 2020-12-09
 */
@Service
public class LoginTokenServiceImpl extends ServiceImpl<LoginTokenMapper, LoginToken> implements LoginTokenService {

    @Resource
    private LoginTokenMapper loginTokenMapper;

    public Boolean existsUserToken(String username, String authToken) {
        HashMap<String, Object> conditions = new HashMap<>();
        conditions.put(com.xh.domain.columns.LoginToken.user, username);
        conditions.put(com.xh.domain.columns.LoginToken.token, authToken);
        return super.getOne(new QueryWrapper<LoginToken>().allEq(conditions)) != null;
    }
    
    @Override
    public void delIfExistsUserSession(String username) {
        QueryWrapper<LoginToken> queryWrapper = new QueryWrapper<LoginToken>().eq(com.xh.domain.columns.LoginToken.user, username)
                .ge(com.xh.domain.columns.LoginToken.expiredt, new Date());
        super.remove(queryWrapper);
    }

    @Override
    @Trace
    public void saveToken(LoginToken token) {
        loginTokenMapper.saveToken(token);
    }

}
