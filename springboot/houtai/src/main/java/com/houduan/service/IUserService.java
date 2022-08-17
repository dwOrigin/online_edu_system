package com.houduan.service;

import com.houduan.common.Result;
import com.houduan.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author online_system
 * @since 2022-08-15
 */
public interface IUserService extends IService<User> {
    Result login(String username,String password);

    Result register(User user);

    User searchByName(String userName);
}
