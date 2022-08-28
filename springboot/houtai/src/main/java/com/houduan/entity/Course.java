package com.houduan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

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
@Data
@ApiModel(value = "Course对象", description = "")
public class Course implements Serializable,Comparable<Course> {

    private static final long serialVersionUID = 1L;

      @TableId(value = "course_id", type = IdType.AUTO)
    private Integer courseId;

    private String courseName;

    private Integer isAvailable;

    private LocalDateTime addTime;

    private Time timeLong;

    private String title;

    private String logo;

    private String type;

    private Integer pageViewcount;

    private Integer praiseCount;

    private Integer commentNum;

    private Integer teacherId;

    private Integer sort;

    @Override
    public int compareTo( Course course) {
        return this.sort.compareTo(course.getSort());
    }
}
