package com.houduan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.houduan.common.Result;
import com.houduan.entity.Msgreceive;
import com.houduan.entity.Msgsystem;
import com.houduan.mapper.MsgreceiveMapper;
import com.houduan.service.IMsgreceiveService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
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
public class MsgreceiveServiceImpl extends ServiceImpl<MsgreceiveMapper, Msgreceive> implements IMsgreceiveService {
@Autowired
private MsgreceiveMapper mapper;
    @Override
    public List<Msgreceive> showMsgReceive(Integer integer) {
        QueryWrapper<Msgreceive> wrapper = new QueryWrapper<>();
        wrapper.eq("receiving_cusid",integer);
        List<Msgreceive> msgreceiveList = mapper.selectList(wrapper);
        return msgreceiveList;
    }
    @Override
    public List<Msgreceive> showMsgSend(Integer integer) {
        QueryWrapper<Msgreceive> wrapper = new QueryWrapper<>();
        wrapper.eq("cus_id",integer);
        List<Msgreceive> msgreceiveList = mapper.selectList(wrapper);
        return msgreceiveList;
    }

    @Override
    public Msgreceive showDetail(Integer integer) {
        QueryWrapper<Msgreceive> wrapper = new QueryWrapper<>();
        wrapper.eq("id",integer);
        Msgreceive msgreceive = mapper.selectOne(wrapper);
        return msgreceive;
    }
    @Override
    public Result deleteMsg(Integer integer) {
        QueryWrapper<Msgreceive> wrapper = new QueryWrapper<>();
        wrapper.eq("id",integer);
        int i = mapper.delete(wrapper);
        if (i>=1){
            return Result.success();
        }else {
            return Result.error();
        }

    }
    @Override
    public Result deleteReceivedMsgPerson(Integer integer) {
        QueryWrapper<Msgreceive> wrapper = new QueryWrapper<>();
        wrapper.eq("receiving_cusid",integer);
        int i = mapper.delete(wrapper);
        if (i>=1){
            return Result.success();
        }else {
            return Result.error();
        }
    }

    @Override
    public Result deleteSendMsgPerson(Integer integer) {
        QueryWrapper<Msgreceive> wrapper = new QueryWrapper<>();
        wrapper.eq("cus_id",integer);
        int i = mapper.delete(wrapper);
        if (i>=1){
            return Result.success();
        }else {
            return Result.error();
        }
    }

    @Override
    public Result autoAddMsgReceive(Msgreceive msgreceive) {
        int i = mapper.insert(msgreceive);
        if (i>=1){
            return Result.success();
        }else
        {
            return Result.error();
        }

    }

    @Override
    public List<Msgreceive> getbyid(Integer id) {
        QueryWrapper<Msgreceive>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("receiving_cusid",id);
        queryWrapper.eq("status",0);
        return list(queryWrapper);
    }

    @Override
    public Result haveread(Integer id) {
         Msgreceive msgreceive=getById(id);
         msgreceive.setStatus(1);
         updateById(msgreceive);
         return Result.success();
    }

    @Override
    public Result readone(Integer cusId, Integer userId) {
        QueryWrapper<Msgreceive>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("cus_id",cusId);
        queryWrapper.eq("receiving_cusid",userId);
        List<Msgreceive>list=list(queryWrapper);
        for(int i=0;i<list.size();i++){
            list.get(i).setStatus(1);
            baseMapper.updateById(list.get(i));
        }
        return Result.success();
    }

    @Override
    public List<Msgreceive> getcus(Integer cusId, Integer userId) {
        QueryWrapper<Msgreceive>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("cus_id",cusId);
        queryWrapper.eq("receiving_cusid",userId);
        return list(queryWrapper);
    }

    @Override
    public Result add(Integer cusId, Integer receiveId, String content) {
        Msgreceive msgreceive=new Msgreceive();
        msgreceive.setCusId(cusId);
        msgreceive.setReceivingCusid(receiveId);
        msgreceive.setContent(content);
        msgreceive.setStatus(0);
        baseMapper.insert(msgreceive);
        return Result.success();
    }

    @Override
    public List<Msgreceive> getboth(Integer cusId, Integer userId) {
        List<Msgreceive>list1=getcus(cusId,userId);
        List<Msgreceive>list2=getcus(userId,cusId);
        list1.addAll(list2);
        Collections.sort(list1);
        return list1;
    }

    @Override
    public List<Msgreceive> getallbyid(Integer id) {
        QueryWrapper<Msgreceive>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("receiving_cusid",id);
        return list(queryWrapper);
    }
}
