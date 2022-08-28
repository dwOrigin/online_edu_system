package com.houduan.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author online_system
 * @since 2022-08-15
 */
@Getter
@Setter
@ApiModel(value = "Comment对象", description = "")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "comment_id", type = IdType.AUTO)
    private Integer commentId;

    private Integer userId;

    private Integer pCommentId;

    private Integer totalId;

    private String content;
    @TableField(fill = FieldFill.INSERT)
    private Date addtime;

    private Integer otherId;

    private Integer praiseCount;

    private Integer replyCount;

    private Integer type;


}
