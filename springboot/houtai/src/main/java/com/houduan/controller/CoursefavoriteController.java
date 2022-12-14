package com.houduan.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.houduan.common.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

import com.houduan.service.ICoursefavoriteService;
import com.houduan.entity.Coursefavorite;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author online_system
 * @since 2022-08-15
 */
@RestController
@RequestMapping("/coursefavorite")
public class CoursefavoriteController {

    @Resource
    private ICoursefavoriteService coursefavoriteService;

    @PostMapping
    public Result savenew(@RequestBody Coursefavorite coursefavorite) {
        return coursefavoriteService.savenew(coursefavorite);
    }
    @PostMapping("/delete")
    public Result deleteone(@RequestBody Coursefavorite coursefavorite){
        return coursefavoriteService.delete(coursefavorite);
    }
    @GetMapping("/getbyuserid")
    public List<Coursefavorite> getbyuserid(@RequestParam Integer userid){
        return coursefavoriteService.getbyuserid(userid);
    }
    @GetMapping
    public List<Coursefavorite> findAll() {
        return coursefavoriteService.list();
    }

    @GetMapping("/{id}")
    public Coursefavorite findOne(@PathVariable Integer id) {
        return coursefavoriteService.getById(id);
    }

    @GetMapping("/page")
    public Page<Coursefavorite> findPage(@RequestParam Integer pageNum,
                                         @RequestParam Integer pageSize) {
        return coursefavoriteService.page(new Page<>(pageNum, pageSize));
    }

}

