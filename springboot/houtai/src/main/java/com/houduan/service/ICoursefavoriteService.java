package com.houduan.service;

import com.houduan.common.Result;
import com.houduan.entity.Coursefavorite;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author online_system
 * @since 2022-08-15
 */
public interface ICoursefavoriteService extends IService<Coursefavorite> {

    Result savenew(Coursefavorite coursefavorite);

    Result delete(Coursefavorite coursefavorite);

    List<Coursefavorite> getbyuserid(Integer userid);
}
