package com.houduan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.houduan.common.Result;
import com.houduan.entity.Article;
import com.houduan.mapper.ArticleMapper;
import com.houduan.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author online_system
 * @since 2022-08-15
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {
@Autowired
private ArticleMapper articleMapper;
    @Override
    public Result addArticle(Article article) {
        article.setCreateTime(LocalDateTime.now());
        int insert = articleMapper.insert(article);
        if (insert>=1){
            return Result.success();
        }else {
            return Result.error();
        }
    }

    @Override
    public Result deleteArticle(Integer integer) {
        int i = articleMapper.deleteById(integer);
        if (i>=1){
            return Result.success();
        }else {
            return Result.error();
        }

    }

    @Override
    public Result updateArticle(Article article) {
        int i = articleMapper.updateById(article);
        if (i>=1){
            return Result.success();
        }else {
            return Result.error();
        }
    }

    @Override
    public Article findArticleByID(Integer articleId) {
        return articleMapper.selectById(articleId);
    }

    @Override
    public List<Article> searchByType(String type) {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.eq("article_type",type);
        List<Article> list = articleMapper.selectList(wrapper);
        return list;
    }
}
