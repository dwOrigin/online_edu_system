package com.houduan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.houduan.common.Constants;
import com.houduan.common.Result;
import com.houduan.entity.Coursevideo;
import com.houduan.mapper.CoursevideoMapper;
import com.houduan.service.ICoursevideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
public class CoursevideoServiceImpl extends ServiceImpl<CoursevideoMapper, Coursevideo> implements ICoursevideoService {

    @Override
    public List<Coursevideo> findbycourseid(Integer courseId) {
        QueryWrapper<Coursevideo>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        return list(queryWrapper);
    }

    @Override
    public Result addnew(Coursevideo coursevideo) {
        if(save(coursevideo)){
            return Result.success(Constants.CODE_200,"成功");
        }else{
            return Result.error(Constants.CODE_500,"失败");
        }
    }

    @Override
    public Integer getnum(Integer courseId) {
        QueryWrapper<Coursevideo>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        return list(queryWrapper).size();
    }
}
