package com.houduan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.houduan.common.Result;
import com.houduan.entity.Course;
import com.houduan.entity.Coursehistory;
import com.houduan.mapper.CourseMapper;
import com.houduan.mapper.CoursehistoryMapper;
import com.houduan.mapper.QuestionscommentMapper;
import com.houduan.service.ICourseService;
import com.houduan.service.ICoursehistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
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
public class CoursehistoryServiceImpl extends ServiceImpl<CoursehistoryMapper, Coursehistory> implements ICoursehistoryService {
    @Autowired
    private ICourseService courseService;
    @Override
    public Result savenew(Coursehistory coursehistory) {
        coursehistory.setTime(LocalDateTime.now());
        if(save(coursehistory)){
            return Result.success();
        }else return Result.error();
    }

    @Override
    public Result delete(Coursehistory coursehistory) {
        QueryWrapper<Coursehistory>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",coursehistory.getUserId());
        queryWrapper.eq("course_id",coursehistory.getCourseId());
        if(remove(queryWrapper)){
            return Result.success();
        }else return Result.error();
    }

    @Override
    public List<Coursehistory> getbyuserid(Integer userid) {
        QueryWrapper<Coursehistory>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",userid);
        return list(queryWrapper);
    }

}
