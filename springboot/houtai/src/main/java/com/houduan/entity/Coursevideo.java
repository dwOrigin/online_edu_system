package com.houduan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
@ApiModel(value = "Coursevideo对象", description = "")
public class Coursevideo implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "video_id", type = IdType.AUTO)
    private Integer videoId;

    private Integer courseId;

    private Integer courseRank;

    private String videoLink;

    private String name;

    private String time;


}
