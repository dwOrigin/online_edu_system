package com.houduan.service;

import com.houduan.common.Result;
import com.houduan.entity.Article;
import com.houduan.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    List<Course> findType(String type);

    Result updatecourse(Course course);

    Result pageviewplus(Integer id);

    Result praiseplus(Integer id);

//    List<Course> getrecommend();
    List<Course>recommendCourses();

}
