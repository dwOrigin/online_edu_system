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
        wrapper.eq("receiveCusid",integer);
        List<Msgreceive> msgreceiveList = mapper.selectList(wrapper);
        return msgreceiveList;
    }
    @Override
    public List<Msgreceive> showMsgSend(Integer integer) {
        QueryWrapper<Msgreceive> wrapper = new QueryWrapper<>();
        wrapper.eq("cusId",integer);
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
        wrapper.eq("receiveCusid",integer);
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
        wrapper.eq("cusId",integer);
        int i = mapper.delete(wrapper);
        if (i>=1){
            return Result.success();
        }else {
            return Result.error();
        }
    }
}
