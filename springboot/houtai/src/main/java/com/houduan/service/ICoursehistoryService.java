package com.houduan.service;

import com.houduan.entity.Coursehistory;
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
public interface ICoursehistoryService extends IService<Coursehistory> {
    List<String> getByUserId3(Integer userid);

}
