package com.houduan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.reflect.SpringReflectionHelper;
import com.houduan.common.Constants;
import com.houduan.common.Result;
import com.houduan.entity.Article;
import com.houduan.entity.Comment;
import com.houduan.entity.Course;
import com.houduan.entity.User;
import com.houduan.mapper.ArticleMapper;
import com.houduan.mapper.CommentMapper;
import com.houduan.mapper.CourseMapper;
import com.houduan.mapper.UserMapper;
import com.houduan.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
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
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

    @Resource
    private  CommentMapper mapper;

   @Resource
   private UserMapper userMapper;
   @Resource
    private CourseMapper courseMapper;
    @Resource
    private ArticleMapper articleMapper;

    @Override
    public Result sendCommentArticle(Integer userId, String commentContent, Integer articleId) {
//        设置文章评论的一些基本属性值
        Comment comment = new Comment();
        comment.setUserId(userId);
        comment.setType(1);//1是文章
        comment.setTotalId(articleId);
        comment.setOtherId(-1);
        comment.setPraiseCount(0);
        comment.setReplyCount(0);
        comment.setContent(commentContent);
////////////////////////待会需要设置关于视频的属性值

        int insert = mapper.insert(comment);
        if (insert>=1){
            return Result.success();
        }else {
            return Result.error();
        }

    }
    @Override
    public Result sendCommentCourse(Integer userId, String  commentContent, Integer courseId,Integer rate) {
        Comment comment = new Comment();
//        设置文章评论的一些基本属性值
        QueryWrapper<Comment>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        if(getOne(queryWrapper)!=null){
            return Result.error(Constants.CODE_500,"请勿重复评论");
        }
        comment.setUserId(userId);
        comment.setContent(commentContent);
        comment.setTotalId(courseId);
        comment.setType(2);//2是课程
        comment.setPraiseCount(rate);
        comment.setReplyCount(0);
        comment.setOtherId(-1);
        int insert = mapper.insert(comment);
        if (insert>=1){
            return Result.success();
        }else {
            return Result.error(Constants.CODE_500,"评论失败");
        }

    }
   /* @Override
    public Result answerComment(User user, Comment parComment,Comment sonComment) {
        //设置评论的一些属性值

        sonComment.setPCommentId(parComment.getCommentId());
        sonComment.setOtherId(parComment.getUserId());
        sonComment.setUserId(user.getUserId());
        sonComment.setTotalId(parComment.getTotalId());
        //给父评论的内容加上一个回复内容
        this.addReply(parComment);
        int i = mapper.insert(sonComment);
        if (i>=1){
            return Result.success();
        }else {
            return Result.error();
        }
    }*/
    @Override
    public Result addPraise(Comment comment) {
      comment.setPraiseCount(comment.getPraiseCount()+1);
        int i = mapper.updateById(comment);
        if (i>=1)
        {
            return Result.success();
        }else {
            return Result.error();
        }
    }

    @Override
    public Result cancelPraise(Comment comment) {
        comment.setPraiseCount(comment.getPraiseCount()-1);
        int i = mapper.updateById(comment);
        if (i>=1)
        {
            return Result.success();
        }else {
            return Result.error();
        }
    }

    @Override
    public Result addReply(Comment comment) {
        comment.setReplyCount(comment.getPraiseCount()+1);
        int i = mapper.updateById(comment);
        if (i>=1){
            return Result.success();
        }else {
            return Result.error();
        }
    }

    @Override
    public List<Comment> showInitComment(Article article) {
        System.out.println(article);
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("type",1)//1是文章
                .eq("total_id",article.getArticleId());
        List<Comment> list = mapper.selectList(wrapper);
        System.out.println(list);
        return list;
    }

    @Override
    public List<Comment> showInitComment(Course course) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("type",2)//2是课程
                .eq("total_id",course.getCourseId());
        List<Comment> list = mapper.selectList(wrapper);
        return list;
    }

    @Override
    public Result deleteComment(Integer commentId) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("comment_id",commentId);
        int i = mapper.delete(wrapper);
        if (i>=1){
            return Result.success();
        }else {
            return Result.error();
        }

    }

    @Override
    public Article getArticleById(Integer id) {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.eq("article_id",id);
        Article article = articleMapper.selectOne(wrapper);
        System.out.println(article);
        System.out.println("-----------------");
        return article;
    }

    @Override
    public Course getCourseById(Integer id) {
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",id);
        Course course = courseMapper.selectOne(wrapper);
        return course;
    }

    @Override
    public User getUserById(Integer id) {
        User user = userMapper.selectById(id);
        return user;
    }

    @Override
    public Comment getbyuser(Integer userId,Integer courseId) {
        QueryWrapper<Comment>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("total_id",courseId);
        queryWrapper.eq("type",2);
        return mapper.selectOne(queryWrapper);
    }


}


