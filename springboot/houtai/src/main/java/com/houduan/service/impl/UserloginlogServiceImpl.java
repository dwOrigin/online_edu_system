package com.houduan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.houduan.entity.Questions;
import com.houduan.entity.Userloginlog;
import com.houduan.mapper.UserloginlogMapper;
import com.houduan.service.IUserloginlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户登录日志表 服务实现类
 * </p>
 *
 * @author online_system
 * @since 2022-08-15
 */
@Service
public class UserloginlogServiceImpl extends ServiceImpl<UserloginlogMapper, Userloginlog> implements IUserloginlogService {

    @Autowired
    UserloginlogMapper mapper;

    @Override
    public List<Userloginlog> getInfoById(Integer user_id) {
        QueryWrapper<Userloginlog> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",user_id);

        return mapper.selectList(wrapper);
    }
}
