package com.houduan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.houduan.common.Result;
import com.houduan.entity.Article;
import com.houduan.entity.Comment;
import com.houduan.entity.Course;
import com.houduan.entity.User;
import com.houduan.mapper.CommentMapper;
import com.houduan.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private  CommentMapper mapper;
    @Override
    public Result sendComment(User user, Comment comment, Article article) {
//        设置文章评论的一些基本属性值
        comment.setUserId(user.getUserId());
        comment.setPCommentId(-1);
        comment.setTotalId(article.getArticleId());
        comment.setOtherId(-1);
        comment.setPraiseCount(0);
        comment.setReplyCount(0);
////////////////////////待会需要设置关于视频的属性值

        int insert = mapper.insert(comment);
        if (insert>=1){
            return Result.success();
        }else {
            return Result.error();
        }

    }
    @Override
    public Result sendComment(User user, Comment comment, Course course) {
//        设置文章评论的一些基本属性值
        comment.setUserId(user.getUserId());
        comment.setPCommentId(-1);
        comment.setTotalId(course.getCourseId());
        comment.setOtherId(-1);
        comment.setPraiseCount(0);
        comment.setReplyCount(0);
        int insert = mapper.insert(comment);
        if (insert>=1){
            return Result.success();
        }else {
            return Result.error();
        }

    }
    @Override
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
    }
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
    public Result cancelReply(Comment comment) {
        return null;
    }

    @Override
    public List<Comment> showInitComment(Article article) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("pCommentId",-1)
                .eq("totalId",article.getArticleId());
        List<Comment> list = mapper.selectList(wrapper);
        return list;
    }

    @Override
    public List<Comment> showInitComment(Course course) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("pCommentId",-1)
                .eq("totalId",course.getCourseId());
        List<Comment> list = mapper.selectList(wrapper);
        return list;
    }

    @Override
    public Result deleteComment(Integer integer) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("commentId",integer);
        int i = mapper.delete(wrapper);
        if (i>=1){
            return Result.success();
        }else {
            return Result.error();
        }

    }



}


