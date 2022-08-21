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
@ApiModel(value = "Article对象", description = "")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "article_id", type = IdType.AUTO)
    private Integer articleId;

    private String title;

    private String summary;

    private String keyWord;

    private String imageUrl;

    private String source;

    private String author;

    private LocalDateTime createTime;

    private String articleType;

    private Integer clickNum;

    private Integer praiseCount;

    private Integer commentNum;

    private Integer sort;


}
