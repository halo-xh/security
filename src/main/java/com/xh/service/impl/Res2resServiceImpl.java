package com.xh.service.impl;

import com.xh.domain.Res2res;
import com.xh.mapper.Res2resMapper;
import com.xh.service.Res2resService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * mapping table: role -function, function - resource 服务实现类
 * </p>
 *
 * @author Xiao Hong
 * @since 2020-12-10
 */
@Service
public class Res2resServiceImpl extends ServiceImpl<Res2resMapper, Res2res> implements Res2resService {

}
