package com.houduan.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.houduan.common.Result;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import com.houduan.service.ITeacherService;
import com.houduan.entity.Teacher;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author online_system
 * @since 2022-08-15
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Resource
    private ITeacherService teacherService;

    @GetMapping("/findAll")
    public List<Teacher> findAll() {
        return teacherService.list();
    }

    @GetMapping("/{id}")
    public Teacher findOne(@PathVariable Integer id) {
        return teacherService.getById(id);
    }

    @GetMapping("/page")
    public Page<Teacher> findPage(@RequestParam Integer pageNum,
                                  @RequestParam Integer pageSize) {
        return teacherService.page(new Page<>(pageNum, pageSize));
    }

    @PostMapping("/addTeacher")
    public Result addTeacher(@RequestBody Teacher teacher) {
        return teacherService.addTeacher(teacher);
    }

    @PostMapping("/updateTeacher")
    public Result updateTeacher(@RequestBody Teacher teacher) {
        return teacherService.updateTeacher(teacher);
    }

    @GetMapping("/removeTeacher")
    public Result removeTeacher(@RequestParam Integer teacherId) {

        return teacherService.deleteTeacher(teacherId);
    }

    @GetMapping("/searchTeacher")
    public List<Teacher> searchTeachers(@PathVariable String str) {
        return teacherService.searchTeacher(str);
    }
}

