package com.houduan.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.houduan.service.ITeacherService;
import com.houduan.entity.Teacher;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author online_system
 * @since 2022-08-12
 */
@RestController
@RequestMapping("/teacher")
    public class TeacherController {

@Resource
private ITeacherService teacherService;

@PostMapping
public Boolean save(@RequestBody Teacher teacher) {
        return teacherService.saveOrUpdate(teacher);
        }

@DeleteMapping("/{id}")
public Boolean delete(@PathVariable Integer id) {
        return teacherService.removeById(id);
        }

@GetMapping
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

        }

