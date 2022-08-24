package com.houduan.service;

import com.houduan.common.Result;
import com.houduan.entity.Teacher;
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
public interface ITeacherService extends IService<Teacher> {
    Result addTeacher(Teacher teacher);

    Result updateTeacher(Teacher teacher);

    Result deleteTeacher(Teacher teacher);

    List<Teacher> searchTeacher(String str);

    void sortTeacher();

    List<Teacher> findAllBySort();
}
