package com.houduan.controller;

import com.houduan.common.Result;
import com.houduan.service.IRecordsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author dw
 * @date 2022/8/25 13:37
 */
@RestController
@RequestMapping("/records")
public class RecordsController {

    @Resource
    private IRecordsService service;
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
    public Result addRecordArticle(@RequestParam Integer userId,@RequestParam Integer commentId){
        Result result = service.addRecordArticle(userId, commentId);
        return result;
    }
    @GetMapping("/reduceRecordCom")
    public Result reduceRecordComment(@RequestParam Integer userId,@RequestParam Integer commentId){
        Result result = service.reduceRecordComment(userId, commentId);
        return result;
    }

    @GetMapping("/reduceRecordArt")
    public Result reduceRecordArticle(@RequestParam Integer userId,@RequestParam Integer commentId){
        Result result = service.reduceRecordArticle(userId, commentId);
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
        Result result = service.addRecordCourseCollect(userId, courseCollectId);
        return result;
    }
    @GetMapping("/reduceRecordCourseCollect")
    public Result reduceRecordCourseCollect(@RequestParam Integer userId,@RequestParam Integer courseCollectId){
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


}







