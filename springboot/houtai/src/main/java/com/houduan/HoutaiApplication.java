package com.houduan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//@MapperScan("com.houduan.mapper")
@SpringBootApplication
public class HoutaiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HoutaiApplication.class, args);
    }

}
