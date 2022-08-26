package com.houduan.service;
import com.houduan.common.Result;
import com.houduan.entity.Article;
import com.houduan.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.houduan.entity.Course;
import com.houduan.entity.User;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author online_system
 * @since 2022-08-15
 */
public interface ICommentService extends IService<Comment> {
    /*
     * 普通用户
     * */
//    发送一个评论
    Result sendComment(User user, Comment comment, Article article);
    Result sendComment(User user, Comment comment, Course course);

    //    回复一个评论
    Result answerComment(User user,Comment parComment,Comment sonComment);

    //前端返回当前评论中最顶级的评论的对象的id值
    //然后再去查找多级评论中的所有内容
    //    返回一个评论的完整内容
// 加一个点赞
    Result addPraise(Comment comment);
    //    取消点赞
    Result cancelPraise(Comment comment);
    //加回复数
    Result addReply(Comment comment);
    //  减少回复，仅用于管理员删除评论后
    Result cancelReply(Comment comment);
    /*
     * 系统端
     * 初始化时，只显示没有父评论的评论
     * 点击详情后才会展示出一个评论中的所有内容
     * */
//    初始化时，只显示没有父评论的评论
    List<Comment>showInitComment(Article article);
    List<Comment>showInitComment(Course course);
    /*
     * 管理员
     *
     * */
//    删除评论，如果删除某条评论后
    Result deleteComment(Integer integer);

    //根据id查找文章
    Article getArticleById(Integer id);
    //    根据id查找课程
    Course getCourseById(Integer id);
    User getUserById(Integer id);

}
