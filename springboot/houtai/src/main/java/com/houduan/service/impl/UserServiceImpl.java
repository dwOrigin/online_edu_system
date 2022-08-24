package com.houduan.service.impl;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.houduan.common.Constants;
import com.houduan.common.Result;
import com.houduan.entity.User;
import com.houduan.mapper.UserMapper;
import com.houduan.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author online_system
 * @since 2022-08-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public Result login(String username, String password) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name",username);
        User user = getOne(wrapper);
        if(user==null){
            return Result.error(Constants.CODE_400,"用户名不存在");
        } else {
            wrapper.eq("password", password);
            User user1 = getOne(wrapper);
            if (user1 == null) {
                return Result.error(Constants.CODE_400, "密码错误");
            } else {
                user.setLastSystemTime(LocalDateTime.now());
                userMapper.updateById(user);
                return Result.success(Constants.CODE_200, "登录成功", user1);
            }
        }
    }

    @Override
    public Result register(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        QueryWrapper<User> wrapper2 = new QueryWrapper<>();
        wrapper.eq("user_name",user.getUserName());
        wrapper2.eq("mobile",user.getMobile());
        User user1 = getOne(wrapper);
        User user2 = getOne(wrapper2);
        if(user1!=null){
            return Result.error(Constants.CODE_400,"用户名重复");
        }
        if(user2!=null){
            return Result.error(Constants.CODE_400,"手机号已被注册");
        }
        save(user);
        return Result.success(Constants.CODE_200,"注册成功");
    }

    @Override
    public User searchByName(String userName) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name",userName);
        return getOne(wrapper);
    }

    @Override
    public Result deleteUser(User user){
        int i = userMapper.deleteById(user.getUserId());
        if(i == 0){
            return Result.error(Constants.CODE_400,"删除失败");
        }else{
            return Result.success(Constants.CODE_200,"删除成功");

        }
    }

    @Override
    public Result updateUser(User user){
        int i = userMapper.updateById(user);
        if(i == 0){
            return Result.error(Constants.CODE_400,"更新失败");
        }else{
            return Result.success(Constants.CODE_200,"更新成功");

        }
    }

    @Override
    public String sendCode(String mobile) {
        String code = String.valueOf(Math.random()).substring(2, 8);
        String TemplateParam = "{\"code\":\""+code+"\"}";
        // 短信模板id
        String TemplateCode = "SMS_154950909";
        //产品名称:云通信短信API产品,开发者无需替换
        String product = "Dysmsapi";
        //产品域名,开发者无需替换
        String domain = "dysmsapi.aliyuncs.com";
        // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
        String accessKeyId = "LTAI5tKguWm2HWg7TfGYeRzP";
        String accessKeySecret = "8EX1U7yKo97fCENTDUT6y8HEesYHki";

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        } catch (ClientException e) {
            throw new RuntimeException(e);
        }
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(mobile);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("阿里云短信测试");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(TemplateCode);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam(TemplateParam);
        try {
            SendSmsResponse response = acsClient.getAcsResponse(request);
            System.out.println(response.getMessage());
        } catch (ClientException e) {
            throw new RuntimeException(e);
        }
        return code;
    }
}
