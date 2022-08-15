package com.houduan.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;

import java.util.Collections;

public class generator {
    public static void main(String[] args) {
        generator();
    }
    private static void generator(){
        FastAutoGenerator.create("jdbc:mysql://120.46.178.233:3306/edu_system?serverTimezone=UTC", "root", "123456")
                .globalConfig(builder -> {
                    builder.author("online_system") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("C:\\Users\\chen9\\Desktop\\online_system\\online_edu_system\\springboot\\houtai\\src\\main\\java\\");//个人要修改
//                            .outputDir("D:\\在线教育平台\\Online\\src\\main\\java\\"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.houduan") // 设置父包名
                            .moduleName(null) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "C:\\Users\\chen9\\Desktop\\online_system\\online_edu_system\\springboot\\houtai\\src\\main\\resources\\mapper\\")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.entityBuilder().enableLombok();//驼峰
                    builder.controllerBuilder().enableHyphenStyle()
                            .enableRestStyle();//
                    builder.addInclude("Article","Comment","Course","CourseFavorite","CourseHistory","MsgReceive","CourseVideo","MsgSystem","Questions","QuestionsComment","Teacher","User","UserLoginLog") // 设置需要生成的表名
                            //,"Comment","Course","CourseFavorite","CourseHistory","MsgReceive","MsgSystem","Questions","QuestionsComment","Teacher","User","UserLoginLog"
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .execute();
    }
}
