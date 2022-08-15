package com.houduan.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.houduan.service.IMsgreceiveService;
import com.houduan.entity.Msgreceive;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author online_system
 * @since 2022-08-15
 */
@RestController
@RequestMapping("/msgreceive")
    public class MsgreceiveController {

@Resource
private IMsgreceiveService msgreceiveService;

@PostMapping
public Boolean save(@RequestBody Msgreceive msgreceive) {
        return msgreceiveService.saveOrUpdate(msgreceive);
        }

@DeleteMapping("/{id}")
public Boolean delete(@PathVariable Integer id) {
        return msgreceiveService.removeById(id);
        }

@GetMapping
public List<Msgreceive> findAll() {
        return msgreceiveService.list();
        }

@GetMapping("/{id}")
public Msgreceive findOne(@PathVariable Integer id) {
        return msgreceiveService.getById(id);
        }

@GetMapping("/page")
public Page<Msgreceive> findPage(@RequestParam Integer pageNum,
@RequestParam Integer pageSize) {
        return msgreceiveService.page(new Page<>(pageNum, pageSize));
        }

        }

