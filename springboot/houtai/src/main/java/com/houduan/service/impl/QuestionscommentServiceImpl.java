package com.houduan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.houduan.common.Result;
import com.houduan.entity.Comment;
import com.houduan.entity.Questions;
import com.houduan.entity.Questionscomment;
import com.houduan.mapper.QuestionsMapper;
import com.houduan.mapper.QuestionscommentMapper;
import com.houduan.service.IQuestionscommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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
@Autowired
private QuestionsMapper questionsMapper;
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
    public List<Questionscomment> getIntactComments(Integer id) {

        QueryWrapper<Questionscomment> wrapper = new QueryWrapper<>();
//        int questionId=questions.getId();
//        找到所有关于该问题的评论
        wrapper.eq("question_id",id);
        List<Questionscomment> questionscommentList = mapper.selectList(wrapper);
        return questionscommentList;
    }

    @Override
    public Result addPraise(Questionscomment questionscomment) {
        questionscomment.setPraiseCount(questionscomment.getPraiseCount()+1);
        int i = mapper.updateById(questionscomment);
        if (i>=1)
        {
            return Result.success();
        }else {
            return Result.error();
        }


    }

    @Override
    public Result cancelPraise(Questionscomment questionscomment) {

        questionscomment.setPraiseCount(questionscomment.getPraiseCount()-1);
        int i = mapper.updateById(questionscomment);
        if (i>=1)
        {
            return Result.success();
        }else {
            return Result.error();
        }
    }

    @Override
    public Integer getMaxPraise() {
        List<Questionscomment> list = mapper.selectList(null);
        Integer integer = new Integer(0);
        integer=list.get(0).getPraiseCount();
for (int i=0;i<list.size();i++){
    if (integer<=list.get(i).getPraiseCount()){
        integer=list.get(i).getPraiseCount();
    }
}
       return integer;
    }

    @Override
    public Result addCommentCount(Integer integer) {
        QueryWrapper<Questions> wrapper = new QueryWrapper<>();
        wrapper.eq("id",integer);
        System.out.println(integer);
        Questions questions = questionsMapper.selectOne(wrapper);
//        将问题的状态设为1，以便前面调用
        questions.setStatus("1");
        System.out.println("-------调用了--------");
        questions.setReplyCount(questions.getReplyCount()+1);
        int i = questionsMapper.updateById(questions);
        if (i>=1){
            return Result.success();
        }else {
            return Result.error();
        }

    }

    @Override
    public Result reduceCommentCount(Integer integer) {
        Questions questions = questionsMapper.selectById(integer);
        questions.setReplyCount(questions.getReplyCount()-1);
        int i = questionsMapper.updateById(questions);
        /**
         *先把对应的数值赋值结束后，
         * 然后再去判断出当前的状态
         * 只有在delete的时候才会出现状态可能为0的情况*/
//        this.orHaveComment(integer);
        if (i>=1){
            return Result.success();
        }else {
            return Result.error();
        }
    }

    @Override
    public Result orHaveComment(Integer questionId) {
        Questions questions = questionsMapper.selectById(questionId);
        QueryWrapper<Questionscomment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("question_id",questionId);
        List<Questionscomment> questionscommentList = mapper.selectList(queryWrapper);
        if(questionscommentList.size()==0){
            questions.setStatus("0");
            int i = questionsMapper.updateById(questions);
            if (i>=1){
                return Result.success();
            }else {
                return Result.error();
            }
        }else {
            return Result.success();
        }
    }


}

