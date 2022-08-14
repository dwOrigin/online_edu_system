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
 * 用户登录日志表
 * </p>
 *
 * @author online_system
 * @since 2022-08-12
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
