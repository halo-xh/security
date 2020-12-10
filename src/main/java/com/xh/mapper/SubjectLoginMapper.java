package com.xh.mapper;

import com.xh.domain.SubjectLogin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Xiao Hong
 * @since 2020-12-09
 */
public interface SubjectLoginMapper extends BaseMapper<SubjectLogin> {
    
    SubjectLogin selectByLoginName(String name);

}
