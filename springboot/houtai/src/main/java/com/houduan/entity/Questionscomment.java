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
@ApiModel(value = "Questionscomment对象", description = "")
public class Questionscomment implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer cusId;

    private Integer questionId;

    private String content;

    private Integer isBest;

    private Integer replyCount;

    private Integer praiseCount;

    private LocalDateTime addTime;


}
