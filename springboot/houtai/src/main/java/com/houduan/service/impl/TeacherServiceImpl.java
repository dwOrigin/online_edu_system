package com.houduan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.houduan.common.Constants;
import com.houduan.common.Result;
import com.houduan.entity.Course;
import com.houduan.entity.Teacher;
import com.houduan.mapper.CourseMapper;
import com.houduan.mapper.TeacherMapper;
import com.houduan.service.ITeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
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
    @Autowired
    CourseMapper courseMapper;

    @Override
    public Result addTeacher(Teacher teacher) {
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        wrapper.eq("name",teacher.getName());
        if(getOne(wrapper)==null){
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
    public Result deleteTeacher(Teacher teacher) {
        int i = mapper.updateById(teacher);
        if(i==0){
            return Result.error(Constants.CODE_400,"删除失败");
        }else {
            return Result.success(Constants.CODE_200,"删除成功");
        }
    }

    @Override
    public List<Teacher> searchTeacher(String str) {
        List<Teacher> teachers = new ArrayList<>();
        teachers = mapper.selectList(new QueryWrapper<Teacher>().like("name",str));
        return teachers;
    }

    @Override
    public void sortTeacher() {
        List<Teacher> teachers = mapper.selectList(null);
        for(Teacher teacher : teachers){
            int teacher_id = teacher.getId();
            QueryWrapper<Course> wrapper1 = new QueryWrapper<>();
            wrapper1.eq("teacher_id",teacher_id);
            List<Course> courses = courseMapper.selectList(wrapper1);
            int sum = 0;
            for(Course course : courses){
                if(course.getTeacherId()==teacher_id){
                    sum +=course.getPageViewcount();
                }
            }
            teacher.setSort(sum);
        }
        teachers.sort((o1, o2) -> o2.getSort() - o1.getSort());
        int i = 1;
        for(Teacher teacher:teachers){
            teacher.setSort(i);
            mapper.updateById(teacher);
            i++;
        }
    }

    @Override
    public List<Teacher> findAllBySort() {
        List<Teacher> teachers = mapper.selectList(null);
        teachers.sort(Comparator.comparingInt(Teacher::getSort));
        return teachers;
    }

}
