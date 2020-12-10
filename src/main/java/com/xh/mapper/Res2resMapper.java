package com.xh.mapper;

import com.xh.domain.Res2res;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * mapping table: role -function, function - resource Mapper 接口
 * </p>
 *
 * @author Xiao Hong
 * @since 2020-12-10
 */
public interface Res2resMapper extends BaseMapper<Res2res> {

    List<Res2res> getResListByParentId(Integer pId);
    
    List<Res2res> getActiveList();

}
