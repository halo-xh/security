package com.xh.mapper;

import com.xh.domain.User2role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * user mapping role. Mapper 接口
 * </p>
 *
 * @author Xiao Hong
 * @since 2020-12-10
 */
public interface User2roleMapper extends BaseMapper<User2role> {

    List<User2role> getByUserId(Integer uid);
}
