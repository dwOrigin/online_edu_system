package com.houduan.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.houduan.common.Result;
import com.houduan.entity.Coursefavorite;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

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
    @GetMapping("/getbyuseridsss")
    public List<Coursehistory> getbyuserid(@RequestParam Integer userid){
        return coursehistoryService.getbyuserid(userid);
    }
//    修改后的getByUserId
    @GetMapping("/getNumberByUserId")
    public Integer getByUserId(@RequestParam Integer userId){
        return coursehistoryService.getByUserId(userId);
    }

    @GetMapping("/deletebyuserid")
    public Result deletebyuserid(@RequestParam Integer userid){
        return coursehistoryService.deletebyuserid(userid);
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
    @GetMapping("/getByUserId")
    public List<String>getThreeHistory(@RequestParam Integer id){
    List<String> stringList = coursehistoryService.getbyuserid3(id);
    return stringList;
}
    @GetMapping("/getByUserIdT")
    public List<String>getThreeHistoryTime(@RequestParam  Integer id){
        return coursehistoryService.getbyuseridTime(id);
    }

}

