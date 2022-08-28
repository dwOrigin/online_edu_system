package com.houduan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.houduan.common.Constants;
import com.houduan.common.Result;
import com.houduan.entity.Teacher;
import com.houduan.mapper.TeacherMapper;
import com.houduan.service.ITeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author online_system
 * @since 2022-08-15
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements ITeacherService {
    @Autowired
    TeacherMapper mapper;

    @Override
    public Result addTeacher(Teacher teacher) {
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        wrapper.eq("name",teacher.getName());
        if(getOne(wrapper)==null){
            teacher.setCreateTime(LocalDateTime.now());
            mapper.insert(teacher);
            return Result.success(Constants.CODE_200,"添加成功");
        }else{
            return Result.error(Constants.CODE_400,"讲师已存在");
        }
    }

    @Override
    public Result updateTeacher(Teacher teacher) {
        int i = mapper.updateById(teacher);
        if(i == 0){
            return Result.error(Constants.CODE_400,"更新失败");
        }else{
            return Result.success(Constants.CODE_200,"更新成功");
        }
    }

    @Override
    public Result deleteTeacher(Integer id) {
        int i = mapper.deleteById(id);
        if(i==0){
            return Result.error(Constants.CODE_400,"删除失败");
        }else {
            return Result.success(Constants.CODE_200,"删除成功");
        }
    }


    @Override
    public List<Teacher> searchTeacher(String str) {
        List<Teacher> teachers  = mapper.selectList(new QueryWrapper<Teacher>().like("name",str));
        return teachers;
    }

   /* @Override
    public List<Teacher> getAll() {
        List<Teacher> teacherList = mapper.selectList(null);
        return teacherList;
    }*/

}
