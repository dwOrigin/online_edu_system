package com.houduan.service;

import com.houduan.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author online_system
 * @since 2022-08-15
 */
public interface ICourseService extends IService<Course> {

    Boolean addnew(Course course);
}
