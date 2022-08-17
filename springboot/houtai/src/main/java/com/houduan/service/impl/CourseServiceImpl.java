package com.houduan.service.impl;

import com.houduan.common.Result;
import com.houduan.entity.Course;
import com.houduan.mapper.CourseMapper;
import com.houduan.service.ICourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author online_system
 * @since 2022-08-15
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {

    @Resource
    private ICourseService courseService;
    @Override
    public Result addnew(Course course) {
        course.setAddTime(LocalDateTime.now());
        course.setCommentNum(0);
        course.setPageViewcount(0);
        course.setPraiseCount(0);
        courseService.save(course);
        return Result.success("200","提交成功");
    }
}
