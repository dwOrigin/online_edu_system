package com.houduan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@ApiModel(value = "Coursehistory对象", description = "")
public class Coursehistory implements Serializable,Comparable<Coursehistory> {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer courseId;

    private Integer userId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime time;

//用来根据某个属性值去做对比的
    @Override
    public int compareTo( Coursehistory coursehistory) {
       return this.id.compareTo(coursehistory.getId());
    }
}
