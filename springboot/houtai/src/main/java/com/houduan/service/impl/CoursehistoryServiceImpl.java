package com.houduan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.houduan.entity.Course;
import com.houduan.entity.Coursehistory;
import com.houduan.mapper.CourseMapper;
import com.houduan.mapper.CoursehistoryMapper;
import com.houduan.service.ICoursehistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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
public class CoursehistoryServiceImpl extends ServiceImpl<CoursehistoryMapper, Coursehistory> implements ICoursehistoryService {
    @Autowired
    CoursehistoryMapper mapper;
    @Autowired
    CourseMapper courseMapper;
    @Override
    public List<String> getByUserId3(Integer userid) {

            QueryWrapper<Coursehistory> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("user_id",userid);
            List<Coursehistory> List = mapper.selectList(queryWrapper);

            //按照id的大小选出最大的三个数值来
            Collections.sort(List);
            System.out.println(List);
            List<Integer>numberList=new ArrayList<>();
//        得到courseId的集合
            int number=List.size()-1;
            for (int i=0;i<3;i++){
                numberList.add(List.get(number-i).getCourseId());
            }
//        寻找对应的course内容
            java.util.List<Course> courseList = courseMapper.selectBatchIds(numberList);
//     导出对应的list的名字
            ArrayList<String> stringArrayList = new ArrayList<>();
            for (int i=0;i<courseList.size();i++){
                stringArrayList.add(courseList.get(i).getCourseName());
            }
        /*List<Coursehistory>listh=list(queryWrapper);
        List<String>lists=new LinkedList<>();
        int num=0;
        for(int i=listh.size()-1;i>0;i--){
            if(num>=3){
                break;
            }
            QueryWrapper<Course>queryWrapper1=new QueryWrapper<>();
            queryWrapper1.eq("course_id",listh.get(i).getCourseId());
            String name=courseService.getOne(queryWrapper1).getCourseName();
            lists.add(num,name);
            num++;
        }*/
            System.out.println(stringArrayList);
            return stringArrayList;


    }
}
