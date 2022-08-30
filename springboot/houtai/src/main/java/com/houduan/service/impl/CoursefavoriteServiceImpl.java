package com.houduan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.houduan.common.Result;
import com.houduan.entity.Coursefavorite;
import com.houduan.mapper.CoursefavoriteMapper;
import com.houduan.service.ICoursefavoriteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
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
public class CoursefavoriteServiceImpl extends ServiceImpl<CoursefavoriteMapper, Coursefavorite> implements ICoursefavoriteService {
@Resource
private CoursefavoriteMapper mapper;
    @Override
    public Result savenew(Coursefavorite coursefavorite) {
        coursefavorite.setAddTime(LocalDateTime.now());
        if(save(coursefavorite)){
            return Result.success();
        }else return Result.error();
    }

    @Override
    public Result delete(Coursefavorite coursefavorite) {
        QueryWrapper<Coursefavorite>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("course_id",coursefavorite.getCourseId());
        queryWrapper.eq("user_id",coursefavorite.getUserId());
        if(remove(queryWrapper)){
            return Result.success();
        }else return Result.error();

    }

    @Override
    public Result deleteFavorite(Integer userId, Integer courseId) {
        QueryWrapper<Coursefavorite> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId)
                .eq("course_id",courseId);
        int i = mapper.delete(wrapper);
        if (i>=1){
            return Result.success();
        }else {
            return Result.error();
        }

    }

    @Override
    public List<Coursefavorite> getbyuserid(Integer userid) {
        QueryWrapper<Coursefavorite>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",userid);
        return list(queryWrapper);
    }
}
