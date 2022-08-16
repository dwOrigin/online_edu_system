package com.houduan.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
    public Result addnew(@RequestBody Course course){
        return courseService.addnew(course);
    }

    @PostMapping
    public Boolean save(@RequestBody Course course) {
        return courseService.saveOrUpdate(course);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return courseService.removeById(id);
    }

    @GetMapping
    public List<Course> findAll() {
        return courseService.list();
    }

    @GetMapping("/{id}")
    public Course findOne(@PathVariable Integer id) {
        return courseService.getById(id);
    }

    @GetMapping("/page")
    public Page<Course> findPage(@RequestParam Integer pageNum,
                                 @RequestParam Integer pageSize) {
        return courseService.page(new Page<>(pageNum, pageSize));
    }

}

