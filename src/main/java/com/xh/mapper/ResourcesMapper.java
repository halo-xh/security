package com.xh.mapper;

import com.xh.domain.Resources;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Xiao Hong
 * @since 2020-12-09
 */
public interface ResourcesMapper extends BaseMapper<Resources> {

    List<Resources> getListByType(String type);
}
