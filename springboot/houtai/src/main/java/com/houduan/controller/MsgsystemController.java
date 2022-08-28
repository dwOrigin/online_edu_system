package com.houduan.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.houduan.common.Result;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import com.houduan.service.IMsgsystemService;
import com.houduan.entity.Msgsystem;


/**
 * <p>
 * 前端控制器
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
    @PostMapping("/add")
    public Result addMessage(@RequestBody Msgsystem msgsystem) {
        return msgsystemService.addMsgSystem(msgsystem);
    }

    @PostMapping("/update")
    public Result updateMessage(@RequestBody Msgsystem msgsystem) {
        Result result = msgsystemService.updateMsgSystem(msgsystem);
        return result;
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteMessage(@PathVariable Integer id) {
        Result result = msgsystemService.deleteMsgSystem(id);
        return result;
    }

    @GetMapping("/findAll")
    public List<Msgsystem> getAllMsg() {
        List<Msgsystem> all = msgsystemService.getAll();
        return all;
    }

    @GetMapping("/page")
    public Page<Msgsystem> findPage(@RequestParam Integer pageNum,
                                    @RequestParam Integer pageSize) {
        return msgsystemService.page(new Page<>(pageNum, pageSize));
    }

    @PostMapping("/sendAllMsg")
    public Result sendAllMsg(@RequestBody String message) {
        Result result = msgsystemService.sendAllMsg(message);
        return result;
    }
    @GetMapping("/getbyid")
    public List<Msgsystem> getbyid(@RequestParam Integer id){
        return msgsystemService.getbyid(id);
    }
    @GetMapping("/read")
    public Result haveread(@RequestParam Integer id){
        return msgsystemService.haveread(id);
    }
    @GetMapping("/readall")
    public Result havereadall(@RequestParam Integer userId){
        return  msgsystemService.havereadall(userId);
    }
}

