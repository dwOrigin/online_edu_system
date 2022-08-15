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
 * @since 2022-08-15
 */
@Getter
@Setter
@ApiModel(value = "Course对象", description = "")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "course_id", type = IdType.AUTO)
    private Integer courseId;

    private String courseName;

    private Integer isAvailable;

    private LocalDateTime addTime;

    private LocalDateTime timeLong;

    private String title;

    private String logo;

    private String type;

    private Integer pageViewcount;

    private Integer praiseCount;

    private Integer commentNum;

    private Integer teacherId;


}
