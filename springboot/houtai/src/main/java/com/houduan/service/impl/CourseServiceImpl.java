package com.houduan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.houduan.common.Constants;
import com.houduan.common.Result;
import com.houduan.entity.Article;
import com.houduan.entity.Course;
import com.houduan.mapper.CourseMapper;
import com.houduan.service.ICourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;

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
@Autowired
private CourseMapper mapper;
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
        queryWrapper.eq("course_id",course.getCourseId());
        if(update(course,queryWrapper)){
            return Result.success(Constants.CODE_200,"更新成功");
        }else{
            return Result.error(Constants.CODE_500,"更新失败");
        }
    }

    @Override
    public Result pageviewplus(Integer id) {
        QueryWrapper<Course>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("course_id",id);
        Course course=getOne(queryWrapper);
        course.setPageViewcount(course.getPageViewcount()+1);
        return Result.success();
    }

    @Override
    public Result praiseplus(Integer id) {
        QueryWrapper<Course>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("course_id",id);
        Course course=getOne(queryWrapper);
        course.setPraiseCount(course.getPraiseCount());
        return Result.success();
    }

    @Override
    public List<Course> recommendCourses() {
        List<Course>fullReturnList=new ArrayList<Course>();
        List<Course>returnList=new ArrayList<Course>();

        List<Course> initCourse = mapper.selectList(null);
        Set<String> getTypeName=new HashSet<String>();
//        文章的种类是固定的几个内容，然后先就随便设置一下吧
        for (int i=0;i<initCourse.size();i++){
            getTypeName.add(initCourse.get(i).getType());
        }
        List typeList = new ArrayList(getTypeName);
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        for (int i=0;i<typeList.size();i++){
            queryWrapper.eq("article_type",typeList.get(i));
            List<Course> courses = mapper.selectList(queryWrapper);
            Collections.sort(courses);
            for (int j=0;j<5;j++){
                fullReturnList.add(courses.get(courses.size()-1-j));
            }
            for (int t=0;t<2;t++){
                returnList.add(fullReturnList.get((int)(0+Math.random()*(4-0+1))));
            }
        }
        return returnList;


    }


}
