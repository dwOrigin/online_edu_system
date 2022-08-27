package com.houduan.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.houduan.common.Constants;
import com.houduan.common.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import com.houduan.service.ICourseService;
import com.houduan.entity.Course;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author online_system
 * @since 2022-08-15
 */
@RestController
@RequestMapping("/course")
public class CourseController {

    @Resource
    private ICourseService courseService;

    @PostMapping("/add")
    public Result addnew(@RequestBody Course course) {
        return courseService.addnew(course);
    }

    @PostMapping("/update")
    public Result updatecourse(@RequestBody Course course) {
        return courseService.updatecourse(course);
    }
//此处在删除的时候返回的变量不要添上路径直接都不要就可以了
    @GetMapping("/delete")
    public Result delete( Integer courseId) {
        if (courseService.removeById(courseId)) {
            return Result.success(Constants.CODE_200, "删除成功");
        } else {
            return Result.error(Constants.CODE_400, "删除失败");
        }
    }

    @GetMapping
    public List<Course> findAll() {
        return courseService.list();
    }

    @GetMapping("/getById")
    public Course findOne(@RequestParam Integer id) {
        return courseService.getById(id);
    }

    @GetMapping("getByType")
    public List<Course> findType(@RequestParam String type) {
        return courseService.findType(type);
    }

    @GetMapping("/pageviewplus")
    public Result pageviewplus(@RequestParam Integer id){
        return courseService.pageviewplus(id);
    }
    @GetMapping("/praiseplus")
    public Result praiseplus(@RequestParam Integer id){
        return courseService.praiseplus(id);
    }
    @GetMapping("/commentplus")
    public Result commentplus(@RequestParam Integer id){
        return courseService.commentplus(id);
    }
    @GetMapping("/getrecommend")
    public List<Course> getrecommend(){
        return courseService.recommendCourses();
    }
    @GetMapping("/addViewPoint")
    public Result addViewPoint(@RequestParam Integer id){
        return courseService.addViewPoint(id);
    }

    @GetMapping("/sortCourses")
    public Result sortCourses(){
        return courseService.sortArticles();
    }
    @GetMapping("/typeRecommend")
    public List<Course> typeRecommend(Integer courseId){
        return courseService.recommendCoursesType(courseId);
    }
    @GetMapping("/getbyteacher")
    public List<Course>getbyteacher(@RequestParam Integer teacherid){
        return courseService.getbyteacher(teacherid);
    }
    @GetMapping("/getbyname")
    public List<Course>getbyname(@RequestParam String name){
        return courseService.getbyname(name);
    }
    @GetMapping("/getbyboth")
    public List<Course>getbyboth(@RequestParam String select,@RequestParam String key){
        return courseService.getbyboth(select,key);
    }
}

