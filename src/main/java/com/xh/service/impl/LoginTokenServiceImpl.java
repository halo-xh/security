package com.xh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xh.domain.LoginToken;
import com.xh.mapper.LoginTokenMapper;
import com.xh.service.LoginTokenService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    public boolean existsUserToken(String username, String authToken) {
        HashMap<String, Object> conditions = new HashMap<>();
        conditions.put(com.xh.domain.columns.LoginToken.user, username);
        conditions.put(com.xh.domain.columns.LoginToken.token, authToken);
        return super.getOne(new QueryWrapper<LoginToken>().allEq(conditions)) != null;
    }

}
