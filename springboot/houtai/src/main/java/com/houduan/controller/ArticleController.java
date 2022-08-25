package com.houduan.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.houduan.common.Result;
import com.houduan.entity.Course;
import jdk.nashorn.internal.runtime.ListAdapter;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.houduan.service.IArticleService;
import com.houduan.entity.Article;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author online_system
 * @since 2022-08-15
 */

@RestController
@RequestMapping("/article")
    public class ArticleController {

@Resource
private IArticleService articleService;
//添加文章信息
@PostMapping
public Result addArticle(@RequestBody Article article){
        return articleService.addArticle(article);
}
//修改文章信息
@PostMapping("/update")
public Result updateArticle(@RequestBody Article article) {
        return articleService.updateArticle(article);
        }
//删除文章
@GetMapping("/delete")
public Result delete(@RequestParam Integer articleId) {
        return articleService.deleteArticle(articleId);
        }

//按照类型查找
        /*为了区分出按照类别和按照id去查找
         *所以在type的前面加上了一个s
        * */
@GetMapping("/getbytype")
public List<Article> searchByType(@RequestParam String type) {
        return articleService.searchByType(type);
        }
//        按照id去查找对应的文章
@GetMapping("/getbyid")
public Article findArticleById(@RequestParam Integer id){
        return articleService.findArticleByID(id);
}
@GetMapping("/findAll")
public List<Article>findAllArticle(){
        List<Article> all = articleService.findAll();
        return all;
}

@GetMapping("/page")
public Page<Article> findPage(@RequestParam Integer pageNum,
@RequestParam Integer pageSize) {
        return articleService.page(new Page<>(pageNum, pageSize));
}
@GetMapping("/getrecommend")
public List<Article> getrecommend(){
                return articleService.recommendArticles();
        }

        }

