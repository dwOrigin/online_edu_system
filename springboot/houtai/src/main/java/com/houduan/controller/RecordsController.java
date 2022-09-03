package com.houduan.controller;

import com.houduan.common.Result;
import com.houduan.entity.Coursefavorite;
import com.houduan.service.ICoursefavoriteService;
import com.houduan.service.IRecordsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author dw
 * @date 2022/8/25 13:37
 */
@RestController
@RequestMapping("/records")
public class RecordsController {

    @Resource
    private IRecordsService service;
    @Resource
    private ICoursefavoriteService coursefavoriteService;
    @GetMapping("/orLikedArt")
    public Integer orLikedArticle(@RequestParam Integer userId,@RequestParam Integer articleId ){
    Integer integer = service.orLikedArticle(userId, articleId);
    return integer;
    }

    @GetMapping("/orLikedCom")
    public Integer orLikedComment(@RequestParam Integer userId,@RequestParam Integer commentId ){
        Integer integer = service.orLikedComment(userId, commentId);
        return integer;
    }

    @GetMapping("/addRecordCom")
    public Result addRecordComment(@RequestParam Integer userId,@RequestParam Integer commentId){
    Result result = service.addRecordComment(userId, commentId);
    return result;
    }

    @GetMapping("/addRecordArt")
    public Result addRecordArticle(@RequestParam Integer userId,@RequestParam Integer articleId){
        Result result = service.addRecordArticle(userId, articleId);
        return result;
    }
    @GetMapping("/reduceRecordCom")
    public Result reduceRecordComment(@RequestParam Integer userId,@RequestParam Integer commentId){
        Result result = service.reduceRecordComment(userId, commentId);
        return result;
    }

    @GetMapping("/reduceRecordArt")
    public Result reduceRecordArticle(@RequestParam Integer userId,@RequestParam Integer articleId){
        Result result = service.reduceRecordArticle(userId, articleId);
        return result;
    }
/*
* ---------------------课程部分---------------------
* */

    @GetMapping("/addRecordCourseLike")
    public Result addRecordCourseLike(@RequestParam Integer userId,@RequestParam Integer courseLikeId){
        Result result = service.addRecordCourseLike(userId, courseLikeId);
        return result;
    }

    @GetMapping("/reduceRecordCourseLike")
    public Result reduceRecordCourseLike(@RequestParam Integer userId,@RequestParam Integer courseLikeId){
        Result result = service.reduceRecordCourseLike(userId, courseLikeId);
        return result;
    }


    @GetMapping("/addRecordCourseCollect")
    public Result addRecordCourseCollect(@RequestParam Integer userId,@RequestParam Integer courseCollectId){
/*----------------------做一下课程收藏的记录----------------------*/
        Coursefavorite coursefavorite = new Coursefavorite();
        coursefavorite.setCourseId(courseCollectId);
        coursefavorite.setUserId(userId);
        coursefavorite.setAddTime(LocalDateTime.now());
        coursefavoriteService.savenew(coursefavorite);
/*------------------------------------------------------------*/

        Result result = service.addRecordCourseCollect(userId, courseCollectId);
        return result;
    }
    @GetMapping("/reduceRecordCourseCollect")
    public Result reduceRecordCourseCollect(@RequestParam Integer userId,@RequestParam Integer courseCollectId){
    /*----------做一下课程收藏的记录----------*/
        coursefavoriteService.deleteFavorite(userId, courseCollectId);
        Result result = service.reduceRecordCourseCollect(userId, courseCollectId);
        return result;
    }

    @GetMapping("/orCollectedCourse")
    public Integer orCollectedCourse(@RequestParam Integer userId,@RequestParam Integer courseId ){
        Integer integer = service.orCollectedCourse(userId, courseId);
        return integer;
    }

    @GetMapping("/orLikedCourse")
    public Integer orLikedCourse(@RequestParam Integer userId,@RequestParam Integer courseId ){
        Integer integer = service.orLikedCourse(userId, courseId);
        return integer;
    }
/*------------------------------------*/
    @GetMapping("/addRecordArticleComment")
    public Result addRecordArticleComment(@RequestParam Integer userId,@RequestParam Integer articleCommentId){
        Result result = service.addRecordArticleComment(userId, articleCommentId);
        return result;
    }
    @GetMapping("/reduceRecordArticleComment")
    public Result reduceRecordArticleComment(@RequestParam Integer userId,@RequestParam Integer articleCommentId){
        Result result = service.reduceRecordArticleComment(userId, articleCommentId);
        return result;
    }
    @GetMapping("/orLikedArticleCom")
    public Integer orLikedArticleComment(@RequestParam Integer userId,@RequestParam Integer articleCommentId ){
        Integer integer = service.orLikedArticleComment(userId, articleCommentId);
        return integer;
    }
    /*---------------2022-8-28---------------*/
    @GetMapping("/deleteACommentLikes")
        public Result deleteACommentLikes(Integer commentId){
            Result result = service.deleteACommentLikes(commentId);
            return result;
        }



}
