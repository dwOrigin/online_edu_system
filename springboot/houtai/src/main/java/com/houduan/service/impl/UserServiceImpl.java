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
        wrapper.eq("username",username);
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
        return null;
    }
}
