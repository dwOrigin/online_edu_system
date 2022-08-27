package com.houduan.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.houduan.common.Result;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import com.houduan.service.ICoursevideoService;
import com.houduan.entity.Coursevideo;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author online_system
 * @since 2022-08-15
 */
@RestController
@RequestMapping("/coursevideo")
public class CoursevideoController {
    @Resource
    private ICoursevideoService service;
    @Resource
    private ICoursevideoService coursevideoService;
    @PostMapping("/save")
    public Result addnew(@RequestBody Coursevideo coursevideo) {
        return coursevideoService.addnew(coursevideo);
    }

    @GetMapping("/findbycourseid")
    public List<Coursevideo> findbycourseid(@RequestParam Integer courseId) {
        System.out.println(courseId);
        return coursevideoService.findbycourseid(courseId);
    }
    @GetMapping("/getnum")
    public Integer getnum(@RequestParam Integer courseId){
        return coursevideoService.getnum(courseId);
    }
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return coursevideoService.removeById(id);
    }

    @GetMapping
    public List<Coursevideo> findAll() {
        return coursevideoService.list();
    }

    @GetMapping("/{id}")
    public Coursevideo findOne(@PathVariable Integer id) {
        return coursevideoService.getById(id);
    }

    @GetMapping("/page")
    public Page<Coursevideo> findPage(@RequestParam Integer pageNum,
                                      @RequestParam Integer pageSize) {
        return coursevideoService.page(new Page<>(pageNum, pageSize));
    }
//    测试通过
@PostMapping("/addCourseVideo")
    public Result addCourseVideo(@RequestBody Coursevideo coursevideo){
    Result result = service.addCourseVideo(coursevideo);
    return result;
}

@GetMapping("/deleteCourseVideo")
    public Result deleteCourseVideo(Integer courseVideoId){
    Result result = service.deleteCourseVideo(courseVideoId);
    return result;
}




}

