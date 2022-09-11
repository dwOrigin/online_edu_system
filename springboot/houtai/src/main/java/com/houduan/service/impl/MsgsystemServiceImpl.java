package com.houduan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.houduan.common.Result;
import com.houduan.entity.Msgreceive;
import com.houduan.entity.Msgsystem;
import com.houduan.entity.User;
import com.houduan.mapper.MsgreceiveMapper;
import com.houduan.mapper.MsgsystemMapper;
import com.houduan.mapper.UserMapper;
import com.houduan.service.IMsgsystemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class MsgsystemServiceImpl extends ServiceImpl<MsgsystemMapper, Msgsystem> implements IMsgsystemService {
@Autowired
private MsgsystemMapper msgsystemMapper;
@Autowired
private UserMapper userMapper;
@Override
    public Result addMsgSystem(Msgsystem msgSystem){
    msgSystem.setStatus(0);
    int i = msgsystemMapper.insert(msgSystem);
    if (i>=1){
        return Result.success();
    }else {
        return Result.error();
    }
    }

    @Override
    public Result sendAllMsg(String message) {
        List<User> userList = userMapper.selectList(null);
        int i=0;

        for (;i<userList.size();i++)
        {
            Msgsystem msgsystem = new Msgsystem();
            msgsystem.setAcceptId(userList.get(i).getUserId());
            msgsystem.setContent(message);
            msgsystem.setStatus(0);
            msgsystemMapper.insert(msgsystem);
        }
        if (i>=1){
            return Result.success();
        }else {
            return Result.error();
        }
    }

    @Override
    public List<Msgsystem> getbyid(Integer id) {
        QueryWrapper<Msgsystem>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("accept_id",id);
        queryWrapper.eq("status",0);
        return list(queryWrapper);
    }

    @Override
    public Result haveread(Integer id) {
        Msgsystem msgsystem=getById(id);
        msgsystem.setStatus(1);
        updateById(msgsystem);
        return Result.success();
    }

    @Override
    public Result havereadall(Integer userId) {
        List<Msgsystem>list= getbyid(userId);
        for(int i=0;i<list.size();i++){
            list.get(i).setStatus(1);
            baseMapper.updateById(list.get(i));
        }
        return Result.success();
    }

    @Override
    public List<Msgsystem> getallbyid(Integer id) {
        QueryWrapper<Msgsystem>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("accept_id",id);
        return list(queryWrapper);
    }

    @Override
    public Result updateMsgSystem(Msgsystem msgsystem){
    int i= msgsystemMapper.updateById(msgsystem);
    if (i>=1){
        return Result.success();
    }else {
        return Result.error();
    }

}

    @Override
    public List<Msgsystem> getAll() {
        List<Msgsystem> msgsystems = msgsystemMapper.selectList(null);
        return msgsystems;
    }

    @Override
    public Result deleteMsgSystem(Integer integer) {
        int insert = msgsystemMapper.deleteById(integer);
        if (insert>=1){
            return Result.success();
        }else {
            return Result.error();
        }
    }




}
