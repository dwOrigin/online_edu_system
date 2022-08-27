package com.houduan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.houduan.common.Constants;
import com.houduan.common.Result;
import com.houduan.entity.Coursevideo;
import com.houduan.mapper.CoursevideoMapper;
import com.houduan.service.ICoursevideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    @Resource
    private CoursevideoMapper mapper;
    @Override
    public List<Coursevideo> findbycourseid(Integer courseId) {
        QueryWrapper<Coursevideo>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("courseId",courseId);
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
    public Result addCourseVideo(Coursevideo coursevideo) {
        Coursevideo init=coursevideo;
        QueryWrapper<Coursevideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",coursevideo.getCourseId());
        List<Coursevideo> list = mapper.selectList(wrapper);
        init.setCourseRank(list.size()+1);
        int i = mapper.insert(init);
        if (i>=1){
                return Result.success();
            }else {
                return Result.error();
            }
    }

    @Override
    public Result deleteCourseVideo(Integer courseVideoId) {
        Coursevideo coursevideo = mapper.selectById(courseVideoId);
        QueryWrapper<Coursevideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",coursevideo.getCourseId());
        int courseRank= coursevideo.getCourseRank();
        int i = mapper.deleteById(courseVideoId);
        List<Coursevideo> coursevideoList = mapper.selectList(wrapper);
        for (int j=0;j<coursevideoList.size();j++){
//            如果是当前的课程的rank值大于该rank，就减一
//            如果是rank值小于该rank，就不改变
            if (coursevideoList.get(j).getCourseRank()>courseRank){
                coursevideoList.get(j).setCourseRank(
                        coursevideoList.get(j).getCourseRank()-1);

            }
        }
//        在最后的时候将所有的值进行一下更新
        for (int h=0;h<coursevideoList.size();h++){
            mapper.updateById(coursevideoList.get(h));
        }
        if(i>=1){
            return Result.success();
        }else {
            return Result.error();
        }

    }
}
