package com.houduan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.houduan.common.Constants;
import com.houduan.common.Result;
import com.houduan.entity.User;
import com.houduan.mapper.UserMapper;
import com.houduan.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author online_system
 * @since 2022-08-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public Result login(String username, String password) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name",username);
        if(getOne(wrapper)==null){
            return Result.error(Constants.CODE_400,"用户名不存在");
        } else if (getOne(wrapper).getPassword().equals(password)) {
            return Result.error(Constants.CODE_400,"密码错误");
        }else{
            return Result.success(Constants.CODE_200,"登录成功");
        }
    }

    @Override
    public Result register(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        QueryWrapper<User> wrapper1 = new QueryWrapper<>();
        QueryWrapper<User> wrapper2 = new QueryWrapper<>();
        wrapper.eq("user_name",user.getUserName());
        wrapper1.eq("email",user.getEmail());
        wrapper2.eq("mobile",user.getMobile());
        User user1 = getOne(wrapper);
        User user2 = getOne(wrapper1);
        if(user1!=null){
            return Result.error(Constants.CODE_400,"用户名重复");
        }
        if(user1!=null){
            return Result.error(Constants.CODE_400,"邮箱已被注册");
        }
        if(user2!=null){
            return Result.error(Constants.CODE_400,"手机号已被注册");
        }
        userMapper.insert(user);
        return Result.success(Constants.CODE_200,"注册成功");
    }

    @Override
    public User searchByName(String userName) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name",userName);
        return getOne(wrapper);
    }
}
