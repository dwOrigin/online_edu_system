package com.houduan.service;

import com.houduan.common.Result;
import com.houduan.entity.Msgreceive;
import com.baomidou.mybatisplus.extension.service.IService;
import com.houduan.entity.Msgsystem;
import com.houduan.entity.User;
import com.houduan.service.impl.MsgreceiveServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.management.relation.RelationSupport;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author online_system
 * @since 2022-08-15
 */
public interface IMsgreceiveService extends IService<Msgreceive> {

/*
* 普通用户端可以看到的
* */
    //    展示个人的消息接收记录
    List<Msgreceive> showMsgReceive(Integer integer);
//       展示个人发送的消息集合
    List<Msgreceive> showMsgSend(Integer integer);
//  查看一条信息的详细内容
    Msgreceive showDetail(Integer integer);
//    删除一条信息
    Result deleteMsg(Integer integer);
/*
* 管理员端
* */
//    清空某一个特定人的全部接收信息
    Result deleteReceivedMsgPerson(Integer integer);
//    清空某一个特定人的全部发送信息
    Result deleteSendMsgPerson(Integer integer);
/*
* 系统端
* 自动将每次的评论信息记录给添加到表里
* */
Result autoAddMsgReceive(Msgreceive msgreceive);

    List<Msgreceive> getbyid(Integer id);

    Result haveread(Integer id);

    Result readone(Integer cusId, Integer userId);

    List<Msgreceive> getcus(Integer cusId, Integer userId);

    Result add(Integer cusId, Integer receiveId, String content);
//   返回所有有关联的对象
    List<User>getConnectUser(Integer userId);
}
