package com.xh.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Version;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Subject implements Serializable {
    
    @TableId(type = IdType.AUTO)
    private Integer sid;
    private String username;
    private String password;
    
    @TableLogic //表字段逻辑处理注解（逻辑删除）
    Integer deleted;
    
    @TableField(fill = FieldFill.INSERT)
    Date uploadDate;
    
    @TableField(fill = FieldFill.INSERT_UPDATE)
    Date modifyDate;
    
    @Version //乐观锁 CAS 比较字段
    Integer version;
    
    
}
