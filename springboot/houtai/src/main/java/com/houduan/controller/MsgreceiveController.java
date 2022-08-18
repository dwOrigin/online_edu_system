package com.houduan.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.houduan.common.Result;
import com.houduan.entity.User;
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


@GetMapping("/allReceiveMsg")
public List<Msgreceive> showReceivedMsg(@RequestBody User user){

        List<Msgreceive> list = msgreceiveService.showMsgReceive(user.getUserId());
        return list;
}


@DeleteMapping("/{id}")
public Boolean delete(@PathVariable Integer id) {
        return msgreceiveService.removeById(id);
        }

@GetMapping("/sendMsg")
public List<Msgreceive> showSendMsg(@RequestBody User user){
        List<Msgreceive> msgreceiveList = msgreceiveService.showMsgSend(user.getUserId());
        return msgreceiveList;
}


@GetMapping("/details")
public Msgreceive detailsMsg(@RequestBody User user) {
        Msgreceive msgreceive = msgreceiveService.showDetail(user.getUserId());
        return msgreceive;

}
/*
* 管理，然后所有的人在列表中，就是只有全部信息
* */
@DeleteMapping("/{id}")
public Result deleteMsg( @PathVariable  Integer id){
        Result result = msgreceiveService.deleteMsg(id);
return result;
}

@DeleteMapping("/admindelRec/{id}")
        public Result adminDeleteReceivedMsg(@PathVariable Integer id){
        Result result = msgreceiveService.deleteReceivedMsgPerson(id);
        return result;

}
@DeleteMapping("/admindelSen/{id}")
        public Result adminDeleteSendMsg(@PathVariable Integer id){
        Result result = msgreceiveService.deleteSendMsgPerson(id);
        return result;
}
        }

