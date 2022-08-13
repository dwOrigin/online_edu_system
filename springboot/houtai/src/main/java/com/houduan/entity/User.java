package com.houduan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
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
 * @since 2022-08-12
 */
@Getter
@Setter
@ApiModel(value = "User对象", description = "")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    private String mobile;

    private String email;

    private String password;

    private String userName;

    private String showName;

    private Integer sex;

    private Integer age;

    private LocalDateTime createTime;

    private Integer isAvailable;

    private String picImg;

    private Integer msgNum;

    private Integer sysMsgNum;

    private LocalDateTime lastSystemTime;


}
