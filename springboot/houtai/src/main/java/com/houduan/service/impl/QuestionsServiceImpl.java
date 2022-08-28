package com.houduan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.houduan.common.Result;
import com.houduan.entity.Questions;
import com.houduan.mapper.QuestionsMapper;
import com.houduan.service.IQuestionsService;
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
public class QuestionsServiceImpl extends ServiceImpl<QuestionsMapper, Questions> implements IQuestionsService {
@Autowired
    private QuestionsMapper mapper;


    @Override
    public Result addQuestion(Questions questions) {
        int insert = mapper.insert(questions);
        if (insert>=1){
            return Result.success();
        }else {
            return Result.error();
        }
    }

    @Override
    public Result deleteQuestion(Integer integer) {
        int i = mapper.deleteById(integer);
        if (i>=1){
            return Result.success();
        }else {
           return Result.error();
        }
    }

    @Override
    public Result updateQuestion(Questions questions) {
        int i = mapper.updateById(questions);
        if (i>=1){
            return Result.success();
        }else {
            return Result.error();
        }
    }

    @Override
    public List<Questions> searchQuestionByType(String type) {
        QueryWrapper<Questions> wrapper = new QueryWrapper<>();
        wrapper.eq("type",type);
        List<Questions> questionsList = mapper.selectList(wrapper);
        return questionsList;
    }

    @Override
    public Questions getById(Integer id) {
        Questions questions = mapper.selectById(id);
        return  questions;
    }

    @Override
    public List<Questions> getbyuserid(Integer userid) {
        QueryWrapper<Questions>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("cus_id",userid);
        return list(queryWrapper);
    }

    @Override
    public Result plusread(Integer id) {
        Questions questions= baseMapper.selectById(id);
        questions.setBrowseCount(questions.getBrowseCount()+1);
        updateById(questions);
        return Result.success();
    }
}
