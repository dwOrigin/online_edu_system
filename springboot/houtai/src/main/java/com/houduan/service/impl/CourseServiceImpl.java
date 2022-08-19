package com.houduan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.houduan.common.Constants;
import com.houduan.common.Result;
import com.houduan.entity.Course;
import com.houduan.mapper.CourseMapper;
import com.houduan.service.ICourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {

    @Override
    public Result addnew(Course course) {
        course.setAddTime(LocalDateTime.now());
        course.setCommentNum(0);
        course.setPageViewcount(0);
        course.setPraiseCount(0);
        save(course);
        return Result.success("200","提交成功");
    }

    @Override
    public List<Course> findType(String type) {
        QueryWrapper<Course>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("type",type);
        return list(queryWrapper);
    }

    @Override
    public Result updatecourse(Course course) {
        QueryWrapper<Course>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("courseId",course.getCourseId());
        if(update(course,queryWrapper)){
            return Result.success(Constants.CODE_200,"更新成功");
        }else{
            return Result.error(Constants.CODE_500,"更新失败");
        }
    }

    @Override
    public Result pageviewplus(Integer id) {
        QueryWrapper<Course>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("courseId",id);
        Course course=getOne(queryWrapper);
        course.setPageViewcount(course.getPageViewcount()+1);
        return Result.success();
    }

    @Override
    public Result praiseplus(Integer id) {
        QueryWrapper<Course>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("courseId",id);
        Course course=getOne(queryWrapper);
        course.setPraiseCount(course.getPraiseCount());
        return Result.success();
    }
}
