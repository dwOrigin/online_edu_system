package com.houduan.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.houduan.common.Result;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.houduan.service.IUserService;
import com.houduan.entity.User;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author online_system
 * @since 2022-08-15
 */
@RestController
@RequestMapping("/user")
    public class UserController {

@Resource
private IUserService userService;

@GetMapping
public List<User> findAll() {
        return userService.list();
        }

@GetMapping("/{id}")
public User findOne(@PathVariable Integer id) {
        return userService.getById(id);
        }

@GetMapping("/page")
public Page<User> findPage(@RequestParam Integer pageNum,
@RequestParam Integer pageSize) {
        return userService.page(new Page<>(pageNum, pageSize));
        }
@PostMapping("/register")
public Result register(@RequestBody User user){return userService.register(user);}

@PostMapping("/login")
public Result login( String username, String password){return userService.login(username,password);}

@PostMapping("/searchByName")
public User searchByName(String username){return userService.searchByName(username);}

@PostMapping("/deleteUser")
public Result deleteUser(@RequestBody User user){return userService.deleteUser(user);}

@PostMapping("/updateUser")
public Result updateUser(@RequestBody User user){return userService.updateUser(user);}
        }

