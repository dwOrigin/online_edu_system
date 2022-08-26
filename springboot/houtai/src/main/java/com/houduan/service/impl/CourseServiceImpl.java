package com.houduan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.houduan.common.Constants;
import com.houduan.common.Result;
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
        return Result.success("200", "提交成功");
    }

    @Override
    public List<Course> findType(String type) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", type);
        return list(queryWrapper);
    }

    @Override
    public Result updatecourse(Course course) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", course.getCourseId());
        if (update(course, queryWrapper)) {
            return Result.success(Constants.CODE_200, "更新成功");
        } else {
            return Result.error(Constants.CODE_500, "更新失败");
        }
    }

    @Override
    public Result pageviewplus(Integer id) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", id);
        Course course = getOne(queryWrapper);
        course.setPageViewcount(course.getPageViewcount() + 1);
        return Result.success();
    }

    @Override
    public Result praiseplus(Integer id) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", id);
        Course course = getOne(queryWrapper);
        course.setPraiseCount(course.getPraiseCount());
        return Result.success();
    }
    @Override
    public List<Course> getbyteacher(Integer teacherid) {
        QueryWrapper<Course>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("teacher_id",teacherid);
        return list(queryWrapper);
    }
    @Override
    public List<Course> recommendCourses() {
        List<Course> fullReturnList = new ArrayList<Course>();
        List<Course> returnList = new ArrayList<Course>();

        List<Course> initCourse = mapper.selectList(null);
        Set<String> getTypeName = new HashSet<String>();
//        文章的种类是固定的几个内容，然后先就随便设置一下吧
        for (int i = 0; i < initCourse.size(); i++) {
            getTypeName.add(initCourse.get(i).getType());
        }
        List typeList = new ArrayList(getTypeName);
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        for (int i = 0; i < typeList.size(); i++) {
            queryWrapper.eq("article_type", typeList.get(i));
            List<Course> courses = mapper.selectList(queryWrapper);
            Collections.sort(courses);
            for (int j = 0; j < 5; j++) {
                fullReturnList.add(courses.get(courses.size() - 1 - j));
            }
            for (int t = 0; t < 2; t++) {
                returnList.add(fullReturnList.get((int) (0 + Math.random() * (4 - 0 + 1))));
            }
        }
        return returnList;

    }

    @Override
    public Result addViewPoint(Integer id) {
        Course course = mapper.selectById(id);
        course.setPageViewcount(course.getPageViewcount() + 1);
        int i = mapper.updateById(course);
        if (i >= 1) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @Override
    public Result sortArticles() {
        /*
         * 文章的推荐规则是按照得分来进行
         * 按照对应数值划分比例
         * 得分越高，被推荐的程度是要越大的
         * fullReturnList.add(articles.get(articles.size()-1-j));
         *
         * */
        int sortScore=0;
        List<Course> initCourses = mapper.selectList(null);
        //按照点击量、点赞量、评论数量为5：3：2的比例去划分
        for (int i=0;i<initCourses.size();i++){
            sortScore=(int)(initCourses.get(i).getPraiseCount()*0.3+
                    initCourses.get(i).getPageViewcount()*0.5+
                    initCourses.get(i).getCommentNum()*0.2);

            initCourses.get(i).setSort(sortScore);
            mapper.updateById(initCourses.get(i));
            System.out.println(initCourses.get(i).getSort());
        }

        return Result.success();

    }
    /*
     * 推荐课程的操作
     *如果是同一个类别的
     * 小于三门就全部推荐
     * 大于三门的就只会随机推荐三门
     *
     * */
    @Override
    public List<Course> recommendCoursesType(Integer id) {
        QueryWrapper<Course> Wrapper = new QueryWrapper<>();
        Wrapper.eq("course_id",id);
        Course course = mapper.selectOne(Wrapper);
        QueryWrapper<Course> Wrapper2 = new QueryWrapper<>();
        Wrapper2.eq("type",course.getType());
        List<Course> initList = mapper.selectList(Wrapper2);
        if (initList.size()>3){
            List<Course> courses = new ArrayList<>();
            for (int i=0;i<3;i++){
                courses.add(initList.get((int)Math.random()*(initList.size()-1)));
            }
            return courses;
        }
        else return initList;
    }

}
