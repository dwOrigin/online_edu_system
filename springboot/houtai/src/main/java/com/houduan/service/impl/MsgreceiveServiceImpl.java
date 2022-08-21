package com.houduan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.houduan.entity.Msgreceive;
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
    MsgreceiveMapper mapper;

    @Override
    public List<Msgreceive> findMsgById(Integer user_id) {
        QueryWrapper<Msgreceive> wrapper = new QueryWrapper<>();
        wrapper.eq("cus_id",user_id);
        return mapper.selectList(wrapper);
    }
}
