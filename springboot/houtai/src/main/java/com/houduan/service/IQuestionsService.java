package com.houduan.service;

import com.houduan.common.Result;
import com.houduan.entity.Questions;
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

public interface IQuestionsService extends IService<Questions> {
//    增加问题
    Result addQuestion(Questions questions);
// 删除问题
    Result deleteQuestion(Integer integer);
// 修改问题
    Result updateQuestion(Questions questions);
// 按类型查找问题
    List<Questions> searchQuestionByType(String type);
    Questions getById(Integer id);

    List<Questions> getbyuserid(Integer userid);
}
