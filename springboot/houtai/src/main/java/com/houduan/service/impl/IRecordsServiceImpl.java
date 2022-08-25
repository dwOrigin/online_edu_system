package com.houduan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.houduan.common.Result;
import com.houduan.entity.Records;
import com.houduan.mapper.RecordsMapper;
import com.houduan.service.IRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author dw
 * @date 2022/8/25 13:41
 */
@Service
public class IRecordsServiceImpl extends ServiceImpl<RecordsMapper, Records> implements IRecordsService {
@Resource
private RecordsMapper recordsMapper;



    @Override
    public Integer orLikedArticle(Integer userId, Integer commentId) {
        /*
         * 返回1代表点赞过了
         * 返回2代表没有点过赞
         *
         * */
        QueryWrapper<Records> recordsQueryWrapper = new QueryWrapper<>();
        recordsQueryWrapper.eq("user_id",userId)
                .eq("xx_id",commentId)
                .eq("type",1);//1是文章
        Records records = recordsMapper.selectOne(recordsQueryWrapper);
        if (records!=null) {
            return 1;
        }else {
            return 2;
        }
    }

    @Override
    public Integer orLikedComment(Integer userId, Integer commentId) {
        /*
         * 返回1代表点赞过了
         * 返回2代表没有点过赞
         *
         * */

        QueryWrapper<Records> Wrapper = new QueryWrapper<>();
        Wrapper.eq("user_id",userId)
                .eq("xx_id",commentId)
                .eq("type",2);//2是评论
        Object records = recordsMapper.selectObjs(Wrapper);
        if (records!=null) {
            return 1;
        }else {
            return 2;
        }

    }

    @Override
    public Result addRecordComment(Integer userId, Integer commentId) {
        Records records = new Records();
        records.setUserId(userId);
        records.setCollectOrLike(2);
        records.setType(2);//2是评论
        records.setXxId(commentId);
        int i = recordsMapper.insert(records);
        if (i>=1){
            return Result.success();
        }else {
            return Result.error();
        }
    }

    @Override
    public Result addRecordArticle(Integer userId, Integer articleId) {
        Records records = new Records();
        records.setUserId(userId);
        records.setCollectOrLike(2);
        records.setType(1);//1是文章
        records.setXxId(articleId);
        int i = recordsMapper.insert(records);
        if (i>=1){
            return Result.success();
        }else {
            return Result.error();
        }
    }

    @Override
    public Result reduceRecordComment(Integer userId, Integer commentId) {
        QueryWrapper<Records> recordsQueryWrapper = new QueryWrapper<>();
        recordsQueryWrapper.eq("user_id",userId)
                .eq("xx_id",commentId)
                .eq("type",2);//2是评论
        int i = recordsMapper.delete(recordsQueryWrapper);
        if (i>=1){
            return Result.success();
        }else {
            return Result.error();
        }
    }

    @Override
    public Result reduceRecordArticle(Integer userId, Integer articleId) {
        QueryWrapper<Records> recordsQueryWrapper = new QueryWrapper<>();
        recordsQueryWrapper.eq("user_id",userId)
                .eq("xx_id",articleId)
                .eq("type",1);//1是文章
        int i = recordsMapper.delete(recordsQueryWrapper);
        if (i>=1){
            return Result.success();
        }else {
            return Result.error();
        }
    }
}
