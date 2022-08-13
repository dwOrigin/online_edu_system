package com.houduan.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
 * @since 2022-08-12
 */
@RestController
@RequestMapping("/article")
    public class ArticleController {

@Resource
private IArticleService articleService;

@PostMapping
public Boolean save(@RequestBody Article article) {
        return articleService.saveOrUpdate(article);
        }

@DeleteMapping("/{id}")
public Boolean delete(@PathVariable Integer id) {
        return articleService.removeById(id);
        }

@GetMapping
public List<Article> findAll() {
        return articleService.list();
        }

@GetMapping("/{id}")
public Article findOne(@PathVariable Integer id) {
        return articleService.getById(id);
        }

@GetMapping("/page")
public Page<Article> findPage(@RequestParam Integer pageNum,
@RequestParam Integer pageSize) {
        return articleService.page(new Page<>(pageNum, pageSize));
        }

        }

