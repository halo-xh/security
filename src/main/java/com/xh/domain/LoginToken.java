package com.xh.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Xiao Hong
 * @since 2020-12-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="LoginToken对象", description="store token")
public class LoginToken implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "tid", type = IdType.ID_WORKER)
    private Integer tid;

    private String token;

    @ApiModelProperty(value = "login name")
    private String user;

    @TableField(fill = FieldFill.INSERT)
    private Date createdt;


}
