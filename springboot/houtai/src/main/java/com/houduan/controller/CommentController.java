package com.houduan.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.houduan.common.Result;
import com.houduan.entity.Article;
import com.houduan.entity.Course;
import com.houduan.entity.User;
import io.swagger.models.auth.In;
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
    public Result sendComArt(Integer userId, Integer commentId, Integer articleId){

    User user = commentService.getUserById(userId);
    Comment comment = commentService.getById(commentId);
    Article article = commentService.getArticleById(articleId);
    Result result = commentService.sendComment(user, comment, article);
    return result;
}
@GetMapping("/sendCourse")
    public Result sendComCou(Integer userId, Integer commentId, Integer courseId){
    User user = commentService.getUserById(userId);
    Comment comment = commentService.getById(commentId);
    Course course = commentService.getCourseById(courseId);
    Result result = commentService.sendComment(user, comment, course);
    return result;
}
@GetMapping("/praise")
    public Result praise(Integer id){
    Comment comment = commentService.getById(id);
    Result result = commentService.addPraise(comment);
    return result;
}
@GetMapping("/cancelPrise")
    public Result deletePraise(Integer commentId){
    Comment comment = commentService.getById(commentId);
    Result result = commentService.cancelPraise(comment);
    return result;
}
@GetMapping("/showA")
    public List<Comment>showCommentArticle(Integer articleId){
    Article article = commentService.getArticleById(articleId);
    List<Comment> comments = commentService.showInitComment(article);
    return comments;
}
@GetMapping("/showC")
public  List<Comment>showCommentCourse(Integer courseId){
    Course course = commentService.getCourseById(courseId);
    List<Comment> comments = commentService.showInitComment(course);
    return comments;
}

@GetMapping("/delete")
    public Result deleteComment( Integer integer){
    Result result = commentService.deleteComment(integer);
    return result;
}









}