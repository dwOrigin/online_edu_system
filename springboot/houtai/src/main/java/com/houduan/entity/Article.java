package com.houduan.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "Article对象", description = "")
public class Article implements Serializable,Comparable<Article> {

    private static final long serialVersionUID = 1L;

      @TableId(value = "article_id", type = IdType.AUTO)
    private Integer articleId;

    private String title;

    private String summary;

    private String keyWord;

    private String imageUrl;

    private String source;

    private String author;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    private String articleType;

    private Integer clickNum;

    private Integer praiseCount;

    private Integer commentNum;

    private Integer sort;


    @Override
    public int compareTo( Article article) {
        return this.sort.compareTo(article.getSort());
    }
}
