package com.houduan.service.impl;

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
        Msgsystem msgsystem = new Msgsystem();
        for (;i<userList.size();i++)
        {
            msgsystem.setAcceptId(userList.get(i).getUserId());
            msgsystem.setContent(message);
        }
        if (i>=1){
            return Result.success();
        }else {
            return Result.error();
        }
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
