package com.houduan.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.houduan.common.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

import com.houduan.service.IQuestionsService;
import com.houduan.entity.Questions;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author online_system
 * @since 2022-08-15
 */
@RestController
@RequestMapping("/questions")
public class QuestionsController {

    @Resource
    private IQuestionsService questionsService;

    @PostMapping
    public Boolean save(@RequestBody Questions questions) {
        questions.setPraiseCount(0);
        questions.setReplyCount(0);
        questions.setBrowseCount(0);
        Result result = questionsService.addQuestion(questions);
        return true;
    }

    @GetMapping("/delete")
    public Boolean delete(@RequestParam Integer id) {
        return questionsService.removeById(id);
    }

    @GetMapping
    public List<Questions> findAll() {
        return questionsService.list();
    }

    @GetMapping("/id")
    public Questions findOne(@RequestParam Integer id) {
        return questionsService.getById(id);
    }

    @GetMapping("/page")
    public Page<Questions> findPage(@RequestParam Integer pageNum,
                                    @RequestParam Integer pageSize) {
        return questionsService.page(new Page<>(pageNum, pageSize));
    }
    @GetMapping("/getbyuserid")
    public List<Questions> getbyuserid(@RequestParam Integer userid){
        return questionsService.getbyuserid(userid);
    }

    @GetMapping("/plusread")
    public Result plusread(@RequestParam Integer id){
        return questionsService.plusread(id);
    }

}

