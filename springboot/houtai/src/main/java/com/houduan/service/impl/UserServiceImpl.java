package com.houduan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cloopen.rest.sdk.BodyType;
import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.houduan.common.Constants;
import com.houduan.common.Result;
import com.houduan.entity.User;
import com.houduan.mapper.UserMapper;
import com.houduan.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

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
            wrapper.eq("password",password);
            User user1=getOne(wrapper);
            if (user1==null) {
                return Result.error(Constants.CODE_400,"密码错误");
            }else{
                return Result.success(Constants.CODE_200,"登录成功",user1);
            }

        }
    }

    @Override
    public Result register(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        QueryWrapper<User> wrapper1 = new QueryWrapper<>();
        QueryWrapper<User> wrapper2 = new QueryWrapper<>();
        wrapper.eq("user_name",user.getUserName());
        wrapper1.eq("email",user.getEmail());
        wrapper2.eq("mobile",user.getMobile());
        User user1 = getOne(wrapper);
        User user2 = getOne(wrapper1);
        if(user1!=null){
            return Result.error(Constants.CODE_400,"用户名重复");
        }
        if(user1!=null){
            return Result.error(Constants.CODE_400,"邮箱已被注册");
        }
        if(user2!=null){
            return Result.error(Constants.CODE_400,"手机号已被注册");
        }
        userMapper.insert(user);
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
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        if(getOne(wrapper)==null){//生产环境请求地址：app.cloopen.com
            String serverIp = "app.cloopen.com";
            //请求端口
            String serverPort = "8883";
            //主账号,登陆云通讯网站后,可在控制台首页看到开发者主账号ACCOUNT SID和主账号令牌AUTH TOKEN
            String accountSId = "8aaf070882b3fb710182b521eb49004f";
            String accountToken = "d7d0562b1d4d4d79b302ec16a4d78091";
            //请使用管理控制台中已创建应用的APPID
            String appId = "8aaf070882b3fb710182b521ec710056";
            CCPRestSmsSDK sdk = new CCPRestSmsSDK();
            sdk.init(serverIp, serverPort);
            sdk.setAccount(accountSId, accountToken);
            sdk.setAppId(appId);
            sdk.setBodyType(BodyType.Type_JSON);
            //随机生成6位数字为验证码
            String code = String.valueOf(Math.random()).substring(2, 8);
            System.out.println(code);
            //需要把验证号转发到的手机号码
            String to = mobile;
            //在短信管理中选择模板ID，我选择的是1
            String templateId = "1";
            //模板参数
            String[] datas = {code,"30分钟"};
            //这里是使用了一个哈希map来存放手机号、模板ID、模板参数
            HashMap<String, Object> result = sdk.sendTemplateSMS(to,templateId,datas);
            //如果返回0000则正常发送，否则返回异常
            if("000000".equals(result.get("statusCode"))){
                //正常返回输出data包体信息（map）
                HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
                Set<String> keySet = data.keySet();
                for(String key:keySet){
                    Object object = data.get(key);
                    System.out.println(key +" = "+object);
                }
                return code;
            }else{
                //异常返回输出错误码和错误信息
                System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
                return null;
            }}
       else{
           return null;
        }

     }
}
