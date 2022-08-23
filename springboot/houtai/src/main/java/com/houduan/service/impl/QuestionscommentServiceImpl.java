package com.houduan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.houduan.common.Result;
import com.houduan.entity.Questions;
import com.houduan.entity.Questionscomment;
import com.houduan.mapper.QuestionscommentMapper;
import com.houduan.service.IQuestionscommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
public class QuestionscommentServiceImpl extends ServiceImpl<QuestionscommentMapper, Questionscomment> implements IQuestionscommentService {
@Autowired
private QuestionscommentMapper mapper;
    @Override
    public Result addComment(Questionscomment questionscomment) {
        questionscomment.setAddTime(LocalDateTime.now());
        int i = mapper.insert(questionscomment);
        if (i>=1){
            return Result.success();
        }else {
            return Result.error();
        }
    }

    @Override
    public Result deleteComment(Integer id) {
        int i = mapper.deleteById(id);
        if (i>=1){
            return Result.success();
        }else {
            return Result.error();
        }
    }

    @Override
    public List<Questionscomment> getIntactComments(Questions questions) {

        QueryWrapper<Questionscomment> wrapper = new QueryWrapper<>();
        int questionId=questions.getId();
//        找到所有关于该问题的评论
        wrapper.eq("question_id",questionId);
        List<Questionscomment> questionscommentList = mapper.selectList(wrapper);
        return questionscommentList;
    }


}
