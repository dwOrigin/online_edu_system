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
    Result updateArticles(Integer articleId, String content);
//    查询文章的信息，用于文章的初始化部分
    Article findArticleByID(Integer articleId);
    List<Article>searchByType(String type);
//    返回所有文章
    List<Article>findAll();
//    文章推荐机制，按照类别
    List<Article>recommendArticles();
//增加文章的浏览量
    Result addViewPoint(Integer id);

//    对文章的推荐程度进行一个按比例排序
    Result sortArticles();

}
