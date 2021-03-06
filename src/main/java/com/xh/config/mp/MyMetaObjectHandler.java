package com.xh.config.mp;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    
    
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createdt",new Date(),metaObject);
        this.setFieldValByName("updatedt",new Date(),metaObject);
    }
    
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updatedt",new Date(),metaObject);
    }
    
    
}
