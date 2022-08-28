package com.houduan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.houduan.common.Result;
import com.houduan.entity.Records;

/**
 * @author dw
 * @date 2022/8/25 13:36
 */
public interface IRecordsService extends IService<Records> {
   /*
   *显示是否点过赞了，如果点过赞了的话
   *就会返回出值为1，否则为0
   * */
Integer orLikedArticle(Integer userId,Integer articleId);
Integer orLikedComment(Integer userId,Integer commentId);
/*
*如果点赞了，那就把记录加上
* */
Result addRecordComment(Integer userId,Integer commentId);
Result addRecordArticle(Integer userId,Integer articleId);
/*
* 如果给取消点赞的话，那就把对应的记录删除
* */
Result reduceRecordComment(Integer userId,Integer commentId);
Result reduceRecordArticle(Integer userId,Integer articleId);

/*
* 添加课程的点赞记录
*
* */
Result addRecordCourseLike(Integer userId,Integer articleId);

/*
* 减少课程的点赞记录
*
* */
Result reduceRecordCourseLike(Integer userId,Integer articleId);
/*
*
* 添加课程的收藏记录
* */
Result addRecordCourseCollect(Integer userId,Integer articleId);
/*
* 减少课程的收藏记录
*
* */
Result reduceRecordCourseCollect(Integer userId,Integer articleId);

/*
*
* 是否点赞了该课程
*
* */
Integer orLikedCourse(Integer userId,Integer articleId);

/*
* 是否收藏了该课程
*
* */
Integer orCollectedCourse(Integer userId,Integer articleId);

/*-----------------------------------------------*/
   Result addRecordArticleComment(Integer userId,Integer articleCommentId);
   Result reduceRecordArticleComment(Integer userId,Integer articleCommentId);
   Integer orLikedArticleComment(Integer userId,Integer articleCommentId);
/*---------------2022-8-28---------------*/
//   删除评论时将所有的点赞记录全部除去
   Result deleteACommentLikes(Integer commentId);



}
