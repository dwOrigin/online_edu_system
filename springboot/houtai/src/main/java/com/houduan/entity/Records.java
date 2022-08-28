package com.houduan.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.models.auth.In;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author dw
 * @date 2022/8/25 13:28
 */
@Getter
@Setter
public class Records {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer xxId;
    private Integer type;
    private Integer collectOrLike;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
}
