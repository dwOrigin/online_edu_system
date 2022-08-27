package com.houduan.service;

import com.houduan.common.Result;
import com.houduan.entity.Coursevideo;
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
public interface ICoursevideoService extends IService<Coursevideo> {

    List<Coursevideo> findbycourseid(Integer courseId);

    Result addnew(Coursevideo coursevideo);

    Integer getnum(Integer courseId);
    Result addCourseVideo(Coursevideo coursevideo);
    Result deleteCourseVideo(Integer courseVideoId);
}
