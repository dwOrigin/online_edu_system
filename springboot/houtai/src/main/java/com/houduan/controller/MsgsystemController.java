package com.houduan.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.houduan.common.Result;
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
 * @since 2022-08-15
 */
@RestController
@RequestMapping("/msgsystem")
    public class MsgsystemController {

@Resource
private IMsgsystemService msgsystemService;
//更新或添加信息
@PostMapping
public Result addMessage(@RequestBody Msgsystem msgsystem) {
            return msgsystemService.addMsgSystem(msgsystem);
}
@PutMapping
public Result updateMessage(@RequestBody Msgsystem msgsystem){
        return msgsystemService.updateMsgSystem(msgsystem);
}
@DeleteMapping("/{id}")
public Result delete(@PathVariable Integer id) {
        return msgsystemService.deleteMsgSystem(id);
        }

@GetMapping
public List<Msgsystem> findAll() {
        return msgsystemService.getAll();
        }

/*@GetMapping("/{id}")
public Msgsystem findOne(@PathVariable Integer id) {
        return msgsystemService.getById(id);
        }*/
/*
* 分页功能后面再说，没有测试
* */
@GetMapping("/page")
public Page<Msgsystem> findPage(@RequestParam Integer pageNum,
@RequestParam Integer pageSize) {
        return msgsystemService.page(new Page<>(pageNum, pageSize));
        }

}

