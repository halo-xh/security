package com.example.demo.mp;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

public class MyMetaObjectHandler implements MetaObjectHandler {
    
    
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("uploadDate",new Date(),metaObject);
        this.setFieldValByName("modifyDate",new Date(),metaObject);
    }
    
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("modifyDate",new Date(),metaObject);
    }
    
    
}
