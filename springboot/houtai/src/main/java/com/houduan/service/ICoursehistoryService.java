package com.houduan.service;

import com.houduan.common.Result;
import com.houduan.entity.Coursefavorite;
import com.houduan.entity.Coursehistory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDateTime;
import java.util.Date;
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

    Result savenew(Coursehistory coursehistory);

    Result delete(Coursehistory coursehistory);

    List<Coursehistory> getbyuserid(Integer userid);

    List<String> getbyuserid3(Integer userid);

    List<String> getbyuseridTime(Integer userid);

    Result deletebyuserid(Integer userid);
    List<Coursehistory> getByUserId(Integer userId);
}
