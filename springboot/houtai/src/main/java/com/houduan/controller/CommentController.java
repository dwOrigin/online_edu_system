package com.houduan.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.houduan.common.Result;
import com.houduan.entity.Article;
import com.houduan.entity.Course;
import com.houduan.entity.User;
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
 * @since 2022-08-15
 */
@RestController
@RequestMapping("/comment")
    public class CommentController {

@Resource
private ICommentService commentService;

/*
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
*/
//新增评论或者是更新评论
/*@GetMapping("/page")
public Page<Comment> findPage(@RequestParam Integer pageNum,
@RequestParam Integer pageSize) {
        return commentService.page(new Page<>(pageNum, pageSize));
        }*/
@GetMapping("/sendArticle")
    public Result sendComArt(@RequestParam User user, @RequestParam Comment comment, @RequestParam Article article){
    Result result = commentService.sendComment(user, comment, article);
    return result;
}
@GetMapping("/sendCourse")
    public Result sendComCou(@RequestParam User user, @RequestParam Comment comment, @RequestParam Course course){
    Result result = commentService.sendComment(user, comment, course);
    return result;
}
@PostMapping("/praise")
    public Result praise(@RequestBody Comment comment){
    Result result = commentService.addPraise(comment);
    return result;
}
@PostMapping("/cancelPrise")
    public Result deletePraise(@RequestParam Comment comment){
    Result result = commentService.cancelPraise(comment);
    return result;
}
@PostMapping("/showA")
    public List<Comment>showCommentArticle(@RequestBody Article article){
    List<Comment> comments = commentService.showInitComment(article);
    return comments;
}
@PostMapping("/showC")
public  List<Comment>showCommentCourse(@RequestBody Course course){
    List<Comment> comments = commentService.showInitComment(course);
    return comments;
}

@DeleteMapping("/delete")
    public Result deleteComment(@PathVariable Integer integer){
    Result result = commentService.deleteComment(integer);
    return result;
}









}