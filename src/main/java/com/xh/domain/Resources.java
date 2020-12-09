package com.xh.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
@ApiModel(value="Resources对象", description="")
public class Resources implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "rid", type = IdType.ID_WORKER)
    private Integer rid;

    private String resname;

    private String restype;

    private String status;

    @TableField(fill = FieldFill.INSERT)
    private Date createdt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedt;

    @Version
    private Integer version;

    @TableLogic
    private String deleted;


}
