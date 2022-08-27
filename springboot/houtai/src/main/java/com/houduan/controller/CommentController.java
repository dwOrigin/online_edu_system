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
//新增评论或者是更新评论
//测试通过
@GetMapping("/sendArticle")
    public Result sendComArt(Integer userId, String commentContent, Integer articleId){
    Result result = commentService.sendCommentArticle(userId, commentContent, articleId);
    return result;
}
//测试通过
@GetMapping("/sendCourse")
    public Result sendComCourse(Integer userId, String commentContent, Integer courseId,Integer rate){
    Result result = commentService.sendCommentCourse(userId, commentContent, courseId,rate);
    return result;
}
//验证通过
@GetMapping("/praise")
    public Result praise(Integer id){
    Comment comment = commentService.getById(id);
    Result result = commentService.addPraise(comment);
    return result;
}
//验证通过
@GetMapping("/cancelPrise")
    public Result deletePraise(Integer commentId){
    Comment comment = commentService.getById(commentId);
    Result result = commentService.cancelPraise(comment);
    return result;
}
//测试通过
@GetMapping("/showA")
    public List<Comment>showCommentArticle(Integer articleId){
    Article article = commentService.getArticleById(articleId);
    System.out.println(article);
    List<Comment> comments = commentService.showInitComment(article);
    return comments;
}
//测试通过
@GetMapping("/showC")
    public  List<Comment>showCommentCourse(Integer courseId){
    Course course = commentService.getCourseById(courseId);
    List<Comment> comments = commentService.showInitComment(course);
    return comments;
}

//测试通过
@GetMapping("/delete")
    public Result deleteComment( Integer commentId){
    Result result = commentService.deleteComment(commentId);
    return result;
}

}