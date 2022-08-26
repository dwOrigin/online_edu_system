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
import java.util.*;

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

    @Override
    public List<Article> findAll() {
        List<Article> articleList = articleMapper.selectList(null);
        return articleList;
    }

    @Override
    public List<Article> recommendArticles() {
/*
* 采用随机数的方式，每次调用出每个类别里面排名前几的课程，将其加入到数组中，
* 然后将该部分的内容返回到前台，
* 此时可能会出现有些课程被重复推荐了两次或者是多次
* 或许去加入一个全局的变量，让每次推荐过的课程，不会再被推荐了
* 当重新登录的时候就重新计数
*
* */
        List<Article>fullReturnList=new ArrayList<Article>();
        List<Article>returnList=new ArrayList<Article>();

        List<Article> initArticles = articleMapper.selectList(null);
        Set<String> getTypeName=new HashSet<String>();
//        文章的种类是固定的几个内容，然后先就随便设置一下吧
        for (int i=0;i<initArticles.size();i++){
            getTypeName.add(initArticles.get(i).getArticleType());
        }
        List typeList = new ArrayList(getTypeName);
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        for (int i=0;i<typeList.size();i++){
            queryWrapper.eq("article_type",typeList.get(i));
            List<Article> articles = articleMapper.selectList(queryWrapper);
            Collections.sort(articles);
            for (int j=0;j<5;j++){
                fullReturnList.add(articles.get(articles.size()-1-j));
            }
            for (int t=0;t<2;t++){
                returnList.add(fullReturnList.get((int)(0+Math.random()*(4-0+1))));
            }
        }
        return returnList;
    }

    @Override
    public Result addViewPoint(Integer id) {
        Article article = articleMapper.selectById(id);
        article.setClickNum(article.getClickNum()+1);
        int i = articleMapper.updateById(article);
        if (i>=1){
            return Result.success();
        }else {
            return Result.error();
        }


    }

    @Override
    public Result sortArticles() {
        /*
        * 文章的推荐规则是按照得分来进行
        * 按照对应数值划分比例
        * 得分越高，被推荐的程度是要越大的
        * fullReturnList.add(articles.get(articles.size()-1-j));
        *
        * */
        int sortScore=0;
        List<Article> initArticles = articleMapper.selectList(null);
        //按照点击量、点赞量、评论数量为5：3：2的比例去划分
        for (int i=0;i<initArticles.size();i++){
            sortScore=(int)(initArticles.get(i).getPraiseCount()*0.3+
                    initArticles.get(i).getClickNum()*0.5+
                    initArticles.get(i).getCommentNum()*0.2);

            initArticles.get(i).setSort(sortScore);
        }

        return Result.success();



    }
}
