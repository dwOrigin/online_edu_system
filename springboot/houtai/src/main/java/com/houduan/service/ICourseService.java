package com.houduan.service;

import com.houduan.common.Result;
import com.houduan.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author online_system
 * @since 2022-08-15
 */
public interface ICourseService extends IService<Course> {

    Result addnew(Course course);
}
