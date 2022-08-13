package com.houduan.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.houduan.service.IMsgsystemService;
import com.houduan.entity.Msgsystem;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author online_system
 * @since 2022-08-12
 */
@RestController
@RequestMapping("/msgsystem")
    public class MsgsystemController {

@Resource
private IMsgsystemService msgsystemService;

@PostMapping
public Boolean save(@RequestBody Msgsystem msgsystem) {
        return msgsystemService.saveOrUpdate(msgsystem);
        }

@DeleteMapping("/{id}")
public Boolean delete(@PathVariable Integer id) {
        return msgsystemService.removeById(id);
        }

@GetMapping
public List<Msgsystem> findAll() {
        return msgsystemService.list();
        }

@GetMapping("/{id}")
public Msgsystem findOne(@PathVariable Integer id) {
        return msgsystemService.getById(id);
        }

@GetMapping("/page")
public Page<Msgsystem> findPage(@RequestParam Integer pageNum,
@RequestParam Integer pageSize) {
        return msgsystemService.page(new Page<>(pageNum, pageSize));
        }

        }

