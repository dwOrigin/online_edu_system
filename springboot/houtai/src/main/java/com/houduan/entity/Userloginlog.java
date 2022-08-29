package com.houduan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户登录日志表
 * </p>
 *
 * @author online_system
 * @since 2022-08-15
 */
@Getter
@Setter
@ApiModel(value = "Userloginlog对象", description = "用户登录日志表")
public class Userloginlog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
      @TableId(value = "log_id", type = IdType.AUTO)
    private Integer logId;

    @ApiModelProperty("登录时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime loginTime;

    @ApiModelProperty("IP")
    private String ip;

    @ApiModelProperty("用户ID")
    private Integer userId;

    @ApiModelProperty("系统名称")
    private String osName;

    @ApiModelProperty("客户端信息")
    private String userAgent;


}
