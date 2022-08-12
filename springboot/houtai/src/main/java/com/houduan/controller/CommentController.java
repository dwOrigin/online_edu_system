package com.houduan.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.houduan.service.ICommentService;
import com.houduan.entity.Comment;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author online_system
 * @since 2022-08-12
 */
@RestController
@RequestMapping("/comment")
    public class CommentController {

@Resource
private ICommentService commentService;

@PostMapping
public Boolean save(@RequestBody Comment comment) {
        return commentService.saveOrUpdate(comment);
        }

@DeleteMapping("/{id}")
public Boolean delete(@PathVariable Integer id) {
        return commentService.removeById(id);
        }

@GetMapping
public List<Comment> findAll() {
        return commentService.list();
        }

@GetMapping("/{id}")
public Comment findOne(@PathVariable Integer id) {
        return commentService.getById(id);
        }

@GetMapping("/page")
public Page<Comment> findPage(@RequestParam Integer pageNum,
@RequestParam Integer pageSize) {
        return commentService.page(new Page<>(pageNum, pageSize));
        }

        }

