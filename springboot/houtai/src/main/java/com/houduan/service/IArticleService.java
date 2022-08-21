package com.houduan.service;

import com.houduan.common.Result;
import com.houduan.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author online_system
 * @since 2022-08-15
 */
public interface IArticleService extends IService<Article> {
//上传文章
    Result addArticle(Article article);
//    删除文章
    Result deleteArticle(Integer integer);
//    修改文章信息
    Result updateArticle(Article article);
//    查询文章的信息，用于文章的初始化部分
    Article findArticleByID(Integer articleId);
    public List<Article>searchByType(String type);
}
