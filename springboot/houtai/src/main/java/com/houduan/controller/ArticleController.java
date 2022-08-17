package com.houduan.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.houduan.common.Result;
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
@PutMapping
public Result updateArticle(@RequestBody Article article) {
        return articleService.updateArticle(article);
        }
//删除文章
@DeleteMapping("/{id}")
public Result delete(@PathVariable Integer id) {
        return articleService.deleteArticle(id);
        }

//按照类型查找
        /*为了区分出按照类别和按照id去查找
         *所以在type的前面加上了一个s
        * */
@GetMapping("/s" +
        "/{type}")
public List<Article> searchByType(@PathVariable String type) {
        return articleService.searchByType(type);
        }
//        按照id去查找对应的文章
@GetMapping("/{id}")
public Article findArticleById(@PathVariable Integer id){
        return articleService.findArticleByID(id);
}
}

/*@GetMapping("/page")
public Page<Article> findPage(@RequestParam Integer pageNum,
@RequestParam Integer pageSize) {
        return articleService.page(new Page<>(pageNum, pageSize));
        }

        }*/

