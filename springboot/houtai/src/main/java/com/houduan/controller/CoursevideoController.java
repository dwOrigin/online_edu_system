package com.houduan.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.houduan.service.ICoursevideoService;
import com.houduan.entity.Coursevideo;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author online_system
 * @since 2022-08-15
 */
@RestController
@RequestMapping("/coursevideo")
    public class CoursevideoController {

@Resource
private ICoursevideoService coursevideoService;

@PostMapping
public Boolean save(@RequestBody Coursevideo coursevideo) {
        return coursevideoService.saveOrUpdate(coursevideo);
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

        }

