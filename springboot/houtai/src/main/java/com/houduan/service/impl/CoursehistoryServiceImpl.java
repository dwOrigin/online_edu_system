package com.houduan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.houduan.common.Constants;
import com.houduan.common.Result;
import com.houduan.entity.Course;
import com.houduan.entity.Coursehistory;
import com.houduan.mapper.CourseMapper;
import com.houduan.mapper.CoursehistoryMapper;
import com.houduan.service.ICoursehistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

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
private CoursehistoryMapper mapper;
@Autowired
private CourseMapper courseMapper;
    @Override
    public Result savenew(Coursehistory coursehistory) {
        coursehistory.setTime(LocalDateTime.now());
        if(save(coursehistory)){
            return Result.success();
        }else return Result.error();
    }

    @Override
    public Result delete(Coursehistory coursehistory) {
        QueryWrapper<Coursehistory>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",coursehistory.getUserId());
        queryWrapper.eq("course_id",coursehistory.getCourseId());
        if(remove(queryWrapper)){
            return Result.success();
        }else return Result.error();
    }

    @Override
    public List<Coursehistory> getbyuserid(Integer userid) {
        QueryWrapper<Coursehistory>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",userid);
        return list(queryWrapper);

    }
    @Override
    public List<String> getbyuserid3(Integer userid) {
        QueryWrapper<Coursehistory>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",userid);
        List<Coursehistory> List = mapper.selectList(queryWrapper);

        //按照id的大小选出最大的三个数值来
        Collections.sort(List);
        System.out.println(List);
        List<Integer>numberList=new ArrayList<>();

//        得到courseId的集合
        int number=List.size()-1;
        if(number>=2) {
            for (int i = 0; i < 3; i++) {
                numberList.add(List.get(number - i).getCourseId());
            }
        }else {
//            在整体的size值的大小小于3的时候，返回出所有的历史记录
            for (int i = 0; i < List.size(); i++) {
                numberList.add(List.get(number - i).getCourseId());
        }
        }
//        寻找对应的course内容
//       List<Course> courseList = courseMapper.selectBatchIds(numberList);
        /**
         * 如果只用82行的代码就会导致只会返回一个值，
         * 原因是在selectbatchIds只能找到一个id
         *
        * */
        List<Course>courseList=new ArrayList<>();
        for (int i=0;i<numberList.size();i++){
            courseList.add(courseMapper.selectById(numberList.get(i)));
        }


        System.out.println(courseList);
//     导出对应的list的名字
        ArrayList<String> stringArrayList = new ArrayList<>();
        for (int i=0;i<courseList.size();i++){
            stringArrayList.add(courseList.get(i).getCourseName());
        }

        System.out.println(stringArrayList);
        return stringArrayList;
    }
//
@Override
public List<String> getbyuseridTime(Integer userid) {
    QueryWrapper<Coursehistory>queryWrapper=new QueryWrapper<>();
    queryWrapper.eq("user_id",userid);
    List<Coursehistory> List = mapper.selectList(queryWrapper);

    //按照id的大小选出最大的三个数值来
    Collections.sort(List);
    System.out.println(List);
    List<String>timeList=new ArrayList<>();
//        得到courseId的集合
//    通过param的值去调整对应的参数，实现size大于等于3就返回3个，小于3就返回所有的个数
    int number=List.size()-1;
    int param=0;
    if (param>=number)
        param=3;
    else {
        param=List.size();
    }
    for (int i=0;i<param;i++){
        String remem="";
        Duration duration= Duration.between(List.get(number-i).getTime(),LocalDateTime.now());
        long days = duration.toDays(); //相差的天数
        long hours = duration.toHours()%24;//相差的小时数
        long minutes = duration.toMinutes()%60;//相差的分钟数
        if(days>0){
            remem=days+"天前";
        }else if(hours>0){
            remem=hours+"小时"+minutes+"分钟前";
        }else{
            remem=minutes+"分钟前";
        }
        timeList.add(remem);
    }
    return timeList;
}

    @Override
    public Result deletebyuserid(Integer userid) {
        QueryWrapper<Coursehistory>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",userid);
        if(remove(queryWrapper)){
            return Result.success(Constants.CODE_200,"删除成功");
        }else return Result.error(Constants.CODE_500,"删除失败");
    }

    @Override
    public Integer getByUserId(Integer userId) {
        QueryWrapper<Coursehistory>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        List<Coursehistory> list = mapper.selectList(queryWrapper);
//        获取不会重复的course的id内容
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i=0;i<list.size();i++){
            hashSet.add(list.get(i).getCourseId());
        }
        List<Integer> courseId=new ArrayList<>(hashSet);

        int size = courseId.size();
        return size;

    }


}
