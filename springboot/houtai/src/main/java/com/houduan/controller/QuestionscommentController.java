package com.houduan.controller;

import com.alibaba.druid.sql.parser.Token;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.houduan.common.Result;
import com.houduan.entity.Questions;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

import com.houduan.service.IQuestionscommentService;
import com.houduan.entity.Questionscomment;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author online_system
 * @since 2022-08-15
 */
@RestController
@RequestMapping("/questionscomment")
    public class QuestionscommentController {

@Resource
private IQuestionscommentService questionscommentService;

//添加评论
@PostMapping
public Result addComment(@RequestBody Questionscomment questionscomment) {
        questionscomment.setAddTime(LocalDateTime.now());
        questionscomment.setIsBest(0);//初始化为0，有人点赞再加上一个
        questionscomment.setPraiseCount(0);
        Result result = questionscommentService.addComment(questionscomment);
        return result;
        }
// 删除评论
@DeleteMapping()
public Result delete(@RequestBody Questionscomment questionscomment) {
        Result deleteComment = questionscommentService.deleteComment(questionscomment);
        return deleteComment;
}

/*
@GetMapping("/page")
public Page<Questionscomment> findPage(@RequestParam Integer pageNum,
@RequestParam Integer pageSize) {
        return questionscommentService.page(new Page<>(pageNum, pageSize));
        }*/
//    某个问题的全部评论
@GetMapping("/detail")
        public List<Questionscomment> getCommentDetail(@RequestBody Questions questions){
        List<Questionscomment> comments = questionscommentService.getIntactComments(questions);
        return comments;
}

}

