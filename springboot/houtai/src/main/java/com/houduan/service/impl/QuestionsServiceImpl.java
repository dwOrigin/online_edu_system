package com.houduan.service.impl;

import com.houduan.entity.Questions;
import com.houduan.mapper.QuestionsMapper;
import com.houduan.service.IQuestionsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author online_system
 * @since 2022-08-12
 */
@Service
public class QuestionsServiceImpl extends ServiceImpl<QuestionsMapper, Questions> implements IQuestionsService {

}
