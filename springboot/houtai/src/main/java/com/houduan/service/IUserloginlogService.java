package com.houduan.service;

import com.houduan.entity.Userloginlog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户登录日志表 服务类
 * </p>
 *
 * @author online_system
 * @since 2022-08-15
 */
public interface IUserloginlogService extends IService<Userloginlog> {
List<Userloginlog> getInfoById(Integer user_id);

}
