package com.houduan.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.houduan.common.Result;
import com.houduan.entity.Comment;
import com.houduan.entity.Questions;
import com.houduan.entity.Questionscomment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author online_system
 * @since 2022-08-15
 */
public interface IQuestionscommentService extends IService<Questionscomment> {
//    增加问题评论
    Result addComment(Questionscomment questionscomment);
/*
  * 删除某个问题的评论
*
*/
    Result deleteComment(Integer id);
//问答的评论是单条的，显示出该问题的所有回答
//    显示出某个问题的全部回答
     List<Questionscomment> getIntactComments(Integer id);
//添加点赞
    Result addPraise(Questionscomment questionscomment);
    //    取消点赞
    Result cancelPraise(Questionscomment questionscomment);
// 获取点赞的最大值
    Integer getMaxPraise();
//    增加评论数
    Result addCommentCount(Integer integer);
//    减少评论数
    Result reduceCommentCount(Integer integer);
//    判断改该评论是否还有回答，如果是没有人去回答的话
//    就将状态设置为0
    Result orHaveComment(Integer questionId);

}
