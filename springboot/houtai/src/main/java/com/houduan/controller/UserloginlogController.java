package com.houduan.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.houduan.service.IUserloginlogService;
import com.houduan.entity.Userloginlog;


/**
 * <p>
 * 用户登录日志表 前端控制器
 * </p>
 *
 * @author online_system
 * @since 2022-08-15
 */
@RestController
@RequestMapping("/userloginlog")
    public class UserloginlogController {

@Resource
private IUserloginlogService userloginlogService;

@PostMapping
public Boolean save(@RequestBody Userloginlog userloginlog) {
        return userloginlogService.saveOrUpdate(userloginlog);
        }

@DeleteMapping("/{id}")
public Boolean delete(@PathVariable Integer id) {
        return userloginlogService.removeById(id);
        }

@GetMapping
public List<Userloginlog> findAll() {
        return userloginlogService.list();
        }

@GetMapping("/{id}")
public Userloginlog findOne(@PathVariable Integer id) {
        return userloginlogService.getById(id);
        }

@GetMapping("/page")
public Page<Userloginlog> findPage(@RequestParam Integer pageNum,
@RequestParam Integer pageSize) {
        return userloginlogService.page(new Page<>(pageNum, pageSize));
        }
@GetMapping("/getInfoById")
public List<Userloginlog> getInfoById(@PathVariable Integer user_id){return userloginlogService.getInfoById(user_id);}

        }

