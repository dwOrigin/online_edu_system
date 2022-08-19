package com.houduan.service;

import com.houduan.common.Result;
import com.houduan.entity.Msgreceive;
import com.houduan.entity.Msgsystem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.houduan.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author online_system
 * @since 2022-08-15
 */
@Service
public interface IMsgsystemService extends IService<Msgsystem> {
//    判断其是否为新的信息
     Boolean orNew(Msgsystem message);
//删除通知
     Result deleteMsgSystem(Integer integer);
//添加通知
     Result addMsgSystem(Msgsystem msgSystem);
//修改通知
     Result updateMsgSystem(Msgsystem msgsystem);
//    初始化界面，返回所有数据
     List<Msgsystem> getAll();
}
