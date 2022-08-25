package com.houduan.controller;

import com.alibaba.druid.sql.parser.Token;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.houduan.common.Result;
import com.houduan.entity.Msgsystem;
import com.houduan.entity.Questions;
import com.houduan.service.IQuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
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
    questionscommentService.addCommentCount(questionscomment.getQuestionId());
    Result result = questionscommentService.addComment(questionscomment);
        return result;
        }
// 删除评论
@GetMapping("/delete")
public Result delete(@RequestParam  Integer id) {
        Result deleteComment = questionscommentService.deleteComment(id);
        questionscommentService.reduceCommentCount(
                questionscommentService.getById(id).getQuestionId());
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
        public List<Questionscomment> getCommentDetail(@RequestParam Integer id){
        List<Questionscomment> comments = questionscommentService.getIntactComments(id);
        return comments;
}
 @GetMapping("/page")
        public Page<Questionscomment> findPage(@RequestParam Integer pageNum,
                                        @RequestParam Integer pageSize) {
                return questionscommentService.page(new Page<>(pageNum, pageSize));
        }
    @GetMapping("/getMaxPrise")
    public Integer getMaxPraise() {
        Integer integer = questionscommentService.getMaxPraise();
        return integer;
    }
    @GetMapping("/addPraise")
    public Result addPraise(@RequestParam  Integer id) {
        Questionscomment questionscomment = questionscommentService.getById(id);
        Result addPraise = questionscommentService.addPraise(questionscomment);
        return addPraise;
    }
    @GetMapping("/reducePraise")
    public Result reducePraise(@RequestParam  Integer id) {
        Questionscomment questionscomment = questionscommentService.getById(id);
        Result addPraise = questionscommentService.cancelPraise(questionscomment);
        return addPraise;
    }

}

