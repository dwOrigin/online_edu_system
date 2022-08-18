package com.houduan.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.houduan.common.Result;
import com.houduan.entity.Coursefavorite;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import com.houduan.service.ICoursehistoryService;
import com.houduan.entity.Coursehistory;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author online_system
 * @since 2022-08-15
 */
@RestController
@RequestMapping("/coursehistory")
public class CoursehistoryController {

    @Resource
    private ICoursehistoryService coursehistoryService;

    @PostMapping
    public Result savenew(@RequestBody Coursehistory coursehistory) {
        return coursehistoryService.savenew(coursehistory);
    }
    @PostMapping("/delete")
    public Result deleteone(@RequestBody Coursehistory coursehistory){
        return coursehistoryService.delete(coursehistory);
    }
    @GetMapping("/getbyuserid")
    public List<Coursehistory> getbyuserid(@RequestParam Integer userid){
        return coursehistoryService.getbyuserid(userid);
    }

    @GetMapping
    public List<Coursehistory> findAll() {
        return coursehistoryService.list();
    }

    @GetMapping("/{id}")
    public Coursehistory findOne(@PathVariable Integer id) {
        return coursehistoryService.getById(id);
    }

    @GetMapping("/page")
    public Page<Coursehistory> findPage(@RequestParam Integer pageNum,
                                        @RequestParam Integer pageSize) {
        return coursehistoryService.page(new Page<>(pageNum, pageSize));
    }

}

