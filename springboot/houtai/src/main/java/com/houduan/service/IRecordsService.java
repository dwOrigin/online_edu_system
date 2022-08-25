package com.houduan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.houduan.common.Result;
import com.houduan.entity.Records;

/**
 * @author dw
 * @date 2022/8/25 13:36
 */
public interface IRecordsService extends IService<Records> {
   /*
   *显示是否点过赞了，如果点过赞了的话
   *就会返回出值为1，否则为0
   * */
Integer orLikedArticle(Integer userId,Integer articleId);
Integer orLikedComment(Integer userId,Integer commentId);
/*
*如果点赞了，那就把记录加上
* */
Result addRecordComment(Integer userId,Integer commentId);
Result addRecordArticle(Integer userId,Integer articleId);
/*
* 如果给取消点赞的话，那就把对应的记录删除
* */
Result reduceRecordComment(Integer userId,Integer commentId);
Result reduceRecordArticle(Integer userId,Integer articleId);

}
