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
import com.houduan.service.IRecordsService;
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
    @Resource
    private IRecordsService recordsService;
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
        queryWrapper.like("type", type);
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
        mapper.updateById(course);
        return Result.success();
    }

    @Override
    public Result praiseplus(Integer id) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", id);
        Course course = getOne(queryWrapper);
        course.setPraiseCount(course.getPraiseCount()+1);
        mapper.updateById(course);
        return Result.success();
    }
    @Override
    public List<Course> getbyteacher(Integer teacherid) {
        QueryWrapper<Course>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("teacher_id",teacherid);
        return list(queryWrapper);
    }

    @Override
    public Result sortCourses() {
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



        /*----------------------------------------*/
        QueryWrapper<Course> Wrapper = new QueryWrapper<>();
        Wrapper.eq("course_id",id);
        Course courseInit = mapper.selectOne(Wrapper);
        System.out.println(courseInit);
        QueryWrapper<Course> Wrapper2 = new QueryWrapper<>();
        Wrapper2.eq("type",courseInit.getType())
                .ne("course_id",courseInit.getCourseId());
        List<Course> initList = mapper.selectList(Wrapper2);
        if (initList.size()>3){
            List<Course> courses = new ArrayList<>();
            int MAXNUM=initList.size();
            List<Integer> integers = new ArrayList<>();//创建个集合用来存储
            for (int i = 0; i < MAXNUM; i++) {
                integers.add(i);
            }
            Collections.shuffle(integers);
            for (int i=0;i<3;i++){
                /*Date date=new Date();
                int seed= Integer.parseInt(String.format("%ts",date));
                Random r= new Random(seed);
                int t=0 + r.nextInt(initList.size()-1 - 0);
                System.out.println(t);
                if (initList.get(t)!=courseInit) {
                    courses.add(initList.get(t));
                }*/
                    System.out.println(initList.get(integers.get(i)));
                    courses.add(initList.get(integers.get(i)));
            }
            return courses;
        }
        else {
            return initList;
        }
    }

    @Override
    public List<Course> recommendCourses() {
        List<Course> fullReturnList = new ArrayList<Course>();
        List<Course> returnList = new ArrayList<Course>();

        List<Course> initCourse = mapper.selectList(null);
        Set<String> getTypeName = new HashSet<String>();
//        种类是固定的几个内容，然后先就随便设置一下吧
        for (int i = 0; i < initCourse.size(); i++) {
            getTypeName.add(initCourse.get(i).getType());
        }
        List<String> typeList = new ArrayList(getTypeName);

        System.out.println(typeList.size());
        int i = 0;
        for (; i < typeList.size(); i++) {
         /*   注意此处的queryWrapper的声明位置，
            如果声明被放在了122行，就会导致出现
            eq的条件越来越多，也就是理论上的.eq().eq().eq()
            这种多重的筛选条件，因此就会出现只有第一个类别是可以出现结果
            但是后面的都没有办法出现结果的情况！！！*/
            QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("type",typeList.get(i) );
            List<Course> courses = mapper.selectList(queryWrapper);
            System.out.println("运行第"+i+"次");
            System.out.println(courses);
            Collections.sort(courses);
//            当某个种类的课程大于5个时，进行如下操作
//            如果该种类的课程小于5个的时候，就将该种类的课程全部加进去
//            --------------------------------------------
            if(courses.size()>=5) {
                for (int j = 0; j < 5; j++) {
                    fullReturnList.add(courses.get(courses.size() - 1 - j));
                }
//              使用这种方式是因为
//              在使用date()传值的时候会出现由于时间过短，出现了类似于伪随机的情况
                int MAXNUM=fullReturnList.size();//其实就是五
                List<Integer> integers = new ArrayList<>();//创建个集合用来存储
                for(int j=0;j<MAXNUM;j++)
                    integers.add(j);
                Collections.shuffle(integers);

                for (int t = 0; t < 2; t++) {
                    int number = integers.get(t);
                    returnList.add(fullReturnList.get( number));
               }
            }else {
                for (int j=0;j<courses.size();j++)
                returnList.add(courses.get(j));
            }
//            --------------------------------------------
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
    public List<Course> getbyname(String name) {
        List<Course> courses  = mapper.selectList(new QueryWrapper<Course>().like("course_name",name));
        return courses;
    }

    @Override
    public List<Course> getbyboth(String select, String key) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        if(select==null&&key==null){
            return null;
        }else if(select!=null&&key==null){
            queryWrapper.eq("type", select);
        }else if(select==null&&key!=null){
            queryWrapper.like("course_name",key);
        }else{
            queryWrapper.eq("type", select);
            queryWrapper.like("course_name",key);
        }
        return mapper.selectList(queryWrapper);
    }

    @Override
    public Result commentplus(Integer id) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", id);
        Course course = getOne(queryWrapper);
        course.setCommentNum(course.getCommentNum()+1);
        mapper.updateById(course);
        return Result.success();
    }

    @Override
    public Result praisedeplus(Integer id) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", id);
        Course course = getOne(queryWrapper);
        course.setPraiseCount(course.getPraiseCount()-1);
        mapper.updateById(course);
        return Result.success();
    }

    @Override
    public Result commentdeplus(Integer id) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", id);
        Course course = getOne(queryWrapper);
        course.setCommentNum(course.getCommentNum()-1);
        mapper.updateById(course);
        return Result.success();
    }

}
