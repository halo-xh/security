package com.xh.service.impl;

import com.xh.common.MyConstants;
import com.xh.config.security.PermitAllConfigAtrribute;
import com.xh.domain.Res2res;
import com.xh.domain.Resources;
import com.xh.mapper.Res2resMapper;
import com.xh.mapper.ResourcesMapper;
import com.xh.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by Xiao Hong on 2020-12-10
 */
public class AuthorityServiceImpl implements AuthorityService {
    
    @Autowired
    private ResourcesMapper resourcesMapper;
    
    @Autowired
    private Res2resMapper res2resMapper;
    
    @Override
    public Map<RequestMatcher, Collection<ConfigAttribute>> initAuthorityMap(boolean permitAllUrl, List<String> whiteUrlList) {
        List<Resources> apiResList = resourcesMapper.getListByType(MyConstants.RES_TYPE_API);
        Map<RequestMatcher, Collection<ConfigAttribute>> map = new HashMap<>(apiResList.size());
        // white url
        if (whiteUrlList != null) {
            for (String url : whiteUrlList) {
                if (!StringUtils.isEmpty(url)) {
                    AntPathRequestMatcher urlMatcher = new AntPathRequestMatcher(url);
                    List<ConfigAttribute> configAttList = new ArrayList<>();
                    ConfigAttribute configAtt = new PermitAllConfigAtrribute();
                    configAttList.add(configAtt);
                    map.put(urlMatcher, configAttList);
                }
            }
        }
        // db config
        List<Res2res> res2resMapping = res2resMapper.getActiveList();
        HashMap<Integer, String> res2RoleMap = new HashMap<>();
        for (Res2res res2res : res2resMapping) {// api res mapping to role id
            if (!res2RoleMap.containsKey(res2res.getResid())) {
                res2RoleMap.put(res2res.getResid(), res2res.getParentid().toString());
            } else {
                res2RoleMap.put(res2res.getResid(), res2RoleMap.get(res2res.getResid()) + "," + res2res.getParentid());
            }
        }
        for (Resources resources : apiResList) {
            Integer apiResId = resources.getRid();
            String path = resources.getPath();
            RequestMatcher matcher = apiPathResolver(path);
            String roles = res2RoleMap.get(apiResId); // "1,4,3.."
            Collection<ConfigAttribute> atts = SecurityConfig.createListFromCommaDelimitedString(roles);
            map.put(matcher, atts);
        }
        //permit all
        if (!permitAllUrl) {
            AntPathRequestMatcher matcher = new AntPathRequestMatcher("/**");
            List<ConfigAttribute> configAttList = new ArrayList<>();
            ConfigAttribute configAttr = new SecurityConfig(AuthenticatedVoter.IS_AUTHENTICATED_FULLY);
            configAttList.add(configAttr);
            map.put(matcher, configAttList);
        }
        
        return map;
    }
    
    @Override
    public Collection<GrantedAuthority> getGrantedAuthorityByLoginName(String loginName) {
        
        return null;
    }
    
    //[POST]/api/test
    private static RequestMatcher apiPathResolver(String apiPath) {
        RequestMatcher matcher = null;
        if (apiPath.contains("[") && apiPath.contains("]")) {
            int endIndex = apiPath.indexOf("]");
            String api = apiPath.substring(endIndex + 1).trim();
            String method = apiPath.substring(1, endIndex);
            matcher = new AntPathRequestMatcher(api, method);
        } else {
            matcher = new AntPathRequestMatcher(apiPath);
        }
        return matcher;
    }
}
